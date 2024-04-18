package hu.progmasters.circlesapp.controller;

import hu.progmasters.circlesapp.dto.incoming.GroupCreationCommand;
import hu.progmasters.circlesapp.dto.outgoing.GroupDetailsItem;
import hu.progmasters.circlesapp.dto.outgoing.GroupSearchList;
import hu.progmasters.circlesapp.dto.outgoing.JoinedGroupList;
import hu.progmasters.circlesapp.dto.outgoing.NotJoinedGroupList;
import hu.progmasters.circlesapp.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final Logger logger = LoggerFactory.getLogger(GroupController.class);
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupCreationCommand command) {
        String username = getUsernameFromContext();
        groupService.createGroup(command, username);
        logger.info("New group has been created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<JoinedGroupList> getJoinedGroupList(@RequestParam Integer page) {
        String username = getUsernameFromContext();
        logger.info("Group list page is requested");
        return new ResponseEntity<>(groupService.getJoinedGroups(username, page), HttpStatus.OK);
    }

    @GetMapping("/notJoined")
    public ResponseEntity<NotJoinedGroupList> getNotJoinedGroupList(@RequestParam Integer page) {
        String username = getUsernameFromContext();
        logger.info("Group list page is requested");
        return new ResponseEntity<>(groupService.getNotJoinedGroups(username, page), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<GroupSearchList> search(@RequestParam String keyword) {
        logger.info("Group search is requested by keyword: '" + keyword + "'");
        return new ResponseEntity<>(groupService.search(keyword), HttpStatus.OK);
    }

    private String getUsernameFromContext() {
        String username = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails loggedInUser) {
            username = loggedInUser.getUsername();
        }
        return username;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDetailsItem> getGroupDetails(@PathVariable Long id) {
        logger.info("Group details are requested");

        Optional<GroupDetailsItem> optionalGroupDetails = groupService.getGroupDetails(id);

        if (optionalGroupDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GroupDetailsItem groupDetails = optionalGroupDetails.get();
        return new ResponseEntity<>(groupDetails, HttpStatus.OK);
    }

}
