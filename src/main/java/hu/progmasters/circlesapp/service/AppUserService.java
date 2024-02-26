package hu.progmasters.circlesapp.service;

import hu.progmasters.circlesapp.domain.AppUser;
import hu.progmasters.circlesapp.dto.incoming.UserCreationCommand;
import hu.progmasters.circlesapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public AppUser registerAppUser(UserCreationCommand userCreationCommand) {

        AppUser appUser = new AppUser(userCreationCommand);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = findUserByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("Username cannot be found");
        }

        String role = appUser.getRole().toString();
        return User.withUsername(appUser.getUsername())
                .authorities(AuthorityUtils.createAuthorityList(role))
                .password(appUser.getPassword())
                .build();

    }


}
