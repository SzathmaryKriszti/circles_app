package hu.progmasters.circlesapp.service;

import hu.progmasters.circlesapp.domain.AppUser;
import hu.progmasters.circlesapp.dto.incoming.UserCreationCommand;
import hu.progmasters.circlesapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser registerAppUser(UserCreationCommand userCreationCommand) {

        userCreationCommand.setPassword(passwordEncoder.encode(userCreationCommand.getPassword()));
        AppUser appUser = new AppUser(userCreationCommand);
        return appUserRepository.save(appUser);
    }
}
