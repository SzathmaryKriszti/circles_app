package hu.progmasters.thefriendzoneapp.service;

import hu.progmasters.thefriendzoneapp.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserService {

    private final AppUserRepository appUserRepository;
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
}
