package hu.progmasters.circlesapp.config;

import hu.progmasters.circlesapp.domain.UserRole;
import hu.progmasters.circlesapp.service.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AppUserService appUserService;


    public SecurityConfig(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/users")
                        .hasRole(UserRole.ROLE_USER.getDisplayRole())
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/groups")
                        .hasRole(UserRole.ROLE_USER.getDisplayRole())
                ) .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/groups")
                        .hasRole(UserRole.ROLE_USER.getDisplayRole())
                ).authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/groups/notJoined")
                        .hasRole(UserRole.ROLE_USER.getDisplayRole())
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/users/registration").permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/users/login").permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/users/logout").permitAll()
                )
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().denyAll()
                )
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .logout(logout -> logout
                        .logoutUrl("/api/users/logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                )
                .userDetailsService(appUserService);
        return http.build();
    }
}
