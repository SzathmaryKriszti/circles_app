package hu.progmasters.circlesapp.service;

import hu.progmasters.circlesapp.domain.AppUser;
import hu.progmasters.circlesapp.domain.Group;
import hu.progmasters.circlesapp.dto.incoming.GroupCreationCommand;
import hu.progmasters.circlesapp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
