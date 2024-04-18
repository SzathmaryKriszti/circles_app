package hu.progmasters.circlesapp.service;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import hu.progmasters.circlesapp.domain.AppUser;
import hu.progmasters.circlesapp.domain.Group;
import hu.progmasters.circlesapp.domain.elastic.GroupSearch;
import hu.progmasters.circlesapp.dto.incoming.GroupCreationCommand;
import hu.progmasters.circlesapp.dto.outgoing.*;
import hu.progmasters.circlesapp.repository.GroupRepository;
import hu.progmasters.circlesapp.repository.elastic.GroupSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;
    private final AppUserService appUserService;
    private final GroupSearchRepository groupSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public GroupService(GroupRepository groupRepository, AppUserService appUserService, GroupSearchRepository groupSearchRepository, ElasticsearchOperations elasticsearchOperations) {
        this.groupRepository = groupRepository;
        this.appUserService = appUserService;
        this.groupSearchRepository = groupSearchRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public Group createGroup(GroupCreationCommand command, String username) {
        AppUser appUser = appUserService.findUserByUsername(username);
        Group group = groupRepository.save(new Group(command, appUser));
        group.setOwner(appUser);
        appUser.addGroup(group);

        groupSearchRepository.save(new GroupSearch(group.getId(), group.getGroupName()));

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


    public GroupSearchList search(String keywords) {
        Query query = MatchQuery.of(m ->
                        m.field("name")
                                .query(keywords)
                                .operator(Operator.And)
                                .fuzziness("AUTO")
                                .boost(null))
                ._toQuery();

        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build();

        SearchHits<GroupSearch> result =
                this.elasticsearchOperations.search(nativeQuery, GroupSearch.class);

        List<GroupSearchListItem> groupSearchListItems = result.stream()
                .map(SearchHit::getContent)
                .map(GroupSearchListItem::new)
                .toList();

        return new GroupSearchList(groupSearchListItems);
    }

    public Optional<GroupDetailsItem> getGroupDetails(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);

        return optionalGroup.map(GroupDetailsItem::new);
    }
}
