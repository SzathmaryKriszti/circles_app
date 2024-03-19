package hu.progmasters.circlesapp.service;

import hu.progmasters.circlesapp.domain.AppUser;
import hu.progmasters.circlesapp.domain.Group;
import hu.progmasters.circlesapp.dto.incoming.GroupCreationCommand;
import hu.progmasters.circlesapp.dto.outgoing.GroupListItem;
import hu.progmasters.circlesapp.dto.outgoing.JoinedGroupList;
import hu.progmasters.circlesapp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;
    private final AppUserService appUserService;

    @Autowired
    public GroupService(GroupRepository groupRepository, AppUserService appUserService) {
        this.groupRepository = groupRepository;
        this.appUserService = appUserService;
    }

    public Group createGroup(GroupCreationCommand command, String username) {
        AppUser appUser = appUserService.findUserByUsername(username);
        Group group = groupRepository.save(new Group(command, appUser));
        group.setOwner(appUser);
        appUser.addGroup(group);
        return group;
    }

    public JoinedGroupList getJoinedGroups(String username, Integer page) {
       AppUser user = appUserService.findUserByUsername(username);
        Pageable pageable = PageRequest.of(page, 7);
        Page<Group> currentPage = groupRepository.findGroupsJoinedByUser(user, pageable);
        List<GroupListItem> groups = currentPage.stream()
                .map(group -> new GroupListItem(user, group))
                .toList();
        return new JoinedGroupList(currentPage.getTotalPages(), groups);
    }
}
