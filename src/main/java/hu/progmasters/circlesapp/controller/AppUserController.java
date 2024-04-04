package hu.progmasters.circlesapp.controller;

import hu.progmasters.circlesapp.dto.incoming.AppUserDetails;
import hu.progmasters.circlesapp.dto.incoming.UserCreationCommand;
import hu.progmasters.circlesapp.dto.outgoing.AppUserProfileDetails;
import hu.progmasters.circlesapp.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;
    private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PreAuthorize("permitAll")
    @PostMapping("/registration")
    public ResponseEntity<Void> register(@RequestBody UserCreationCommand userCreationCommand) {
        appUserService.registerAppUser(userCreationCommand);
        logger.info("New user has been successfully registered");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/login")
    public ResponseEntity<AppUserDetails> login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserDetails loggedInUser) {
            AppUserDetails loggedInUserDetails = new AppUserDetails();
            loggedInUserDetails.setUsername(loggedInUser.getUsername());
            loggedInUserDetails.setRoles(loggedInUser.getAuthorities().stream().map(Object::toString).toList());
            logger.info("User has been successfully logged in");
            return new ResponseEntity<>(loggedInUserDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<AppUserProfileDetails> getUserProfileDetails() {
        String username = getUsernameFromContext();

        AppUserProfileDetails appUserProfileDetails = appUserService.getUserProfileDetails(username);
        if (appUserProfileDetails != null){
            logger.info("User profile details is requested");
            return new ResponseEntity<>(appUserProfileDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private static String getUsernameFromContext() {
        String username = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails loggedInUser) {
            username = loggedInUser.getUsername();
        }
        return username;
    }

    @GetMapping("/auth/validate-username/{username}")
    public ResponseEntity<Boolean> checkIsUsernameInUse(@PathVariable String username) {
        return new ResponseEntity<Boolean>(appUserService.checkIsUsernameInUse(username), HttpStatus.OK);
    }

}
