package hu.progmasters.circlesapp.controller;

import hu.progmasters.circlesapp.dto.incoming.UserCreationCommand;
import hu.progmasters.circlesapp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserCreationCommand userCreationCommand) {
        appUserService.registerAppUser(userCreationCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
