package hu.progmasters.circlesapp.service;

import hu.progmasters.circlesapp.config.RabbitConfig;
import hu.progmasters.circlesapp.domain.AppUser;
import hu.progmasters.circlesapp.domain.Group;
import hu.progmasters.circlesapp.dto.incoming.GroupCreationCommand;
import hu.progmasters.circlesapp.dto.outgoing.*;
import hu.progmasters.circlesapp.repository.GroupRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;
    private final AppUserService appUserService;
    private final RabbitTemplate rabbitTemplate;



    @Autowired
    public GroupService(GroupRepository groupRepository, AppUserService appUserService, RabbitTemplate rabbitTemplate) {
        this.groupRepository = groupRepository;
        this.appUserService = appUserService;
        this.rabbitTemplate = rabbitTemplate;

    }

    public Group createGroup(GroupCreationCommand command, String username) {
        AppUser appUser = appUserService.findUserByUsername(username);
        Group group = groupRepository.save(new Group(command, appUser));
        group.setOwner(appUser);
        appUser.addGroup(group);

        System.out.println("Group created by " + group.getGroupName());

        // RabbitMQ message sending
        Map<String, String> message = new HashMap<>();
        message.put("groupName", group.getGroupName());

        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "group.created", message);
        System.out.println("Message sent to RabbitMQ about group creation");

        return group;
    }

    public JoinedGroupList getJoinedGroups(String username, Integer page) {
        AppUser user = appUserService.findUserByUsername(username);
        int pagesToLoad = 7;
        if (page > 0) {
            pagesToLoad = 8;
        }
        Pageable pageable = PageRequest.of(page, pagesToLoad);
        Page<Group> currentPage = groupRepository.findGroupsJoinedByUser(user, pageable);
        List<GroupListItem> groups = currentPage.stream()
                .map(group -> new GroupListItem(user, group))
                .collect(Collectors.toList());
        return new JoinedGroupList(currentPage.getTotalPages(), groups);
    }

    public NotJoinedGroupList getNotJoinedGroups(String username, Integer page) {
        AppUser user = appUserService.findUserByUsername(username);
        Pageable pageable = PageRequest.of(page, 7);
        Page<Group> currentPage = groupRepository.findGroupsNotJoinedByUser(user, pageable);
        List<GroupListItem> groups = currentPage.stream()
                .map(group -> new GroupListItem(user, group))
                .toList();
        return new NotJoinedGroupList(currentPage.getTotalPages(), groups);
    }


//    public GroupSearchList search(String keywords) {
//        Query query = MatchQuery.of(m ->
//                        m.field("name")
//                                .query(keywords)
//                                .operator(Operator.And)
//                                .fuzziness("AUTO")
//                                .boost(null))
//                ._toQuery();
//
//        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build();
//
//        SearchHits<GroupSearch> result =
//                this.elasticsearchOperations.search(nativeQuery, GroupSearch.class);
//
//        List<GroupSearchListItem> groupSearchListItems = result.stream()
//                .map(SearchHit::getContent)
//                .map(GroupSearchListItem::new)
//                .toList();
//
//        return new GroupSearchList(groupSearchListItems);
//    }

    public Optional<GroupDetailsItem> getGroupDetails(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);

        return optionalGroup.map(GroupDetailsItem::new);
    }

    public Group joinGroup(Long groupId, String username) {
        Group group = null;
        AppUser user = appUserService.findUserByUsername(username);
        Optional<Group> optionalGroup = findGroupById(groupId);
        if (optionalGroup.isPresent()) {
            group = optionalGroup.get();
            group.addUser(user);
            return group;
        }
        return group;
    }

    private Optional<Group> findGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }
}
