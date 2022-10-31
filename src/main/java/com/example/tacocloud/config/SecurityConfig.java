package com.example.tacocloud.config;

import com.example.tacocloud.dto.User;
import com.example.tacocloud.repo.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        List<UserDetails> usersList = new ArrayList<>();
//        usersList.add(new User(
//                "buzz", encoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
//        ));
//        usersList.add(new User(
//                "woody", encoder.encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
//        ));
//        return new InMemoryUserDetailsManager(usersList);
//    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null)
                return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('USER')")
//                .antMatchers(HttpMethod.POST, "/api/ingredients").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                .antMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
//                .antMatchers(HttpMethod.DELETE, "/api/ingredients/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/ingredients/**").hasAuthority("SCOPE_deleteIngredients")
                .antMatchers("/", "/**").access("permitAll()")
                .and()
                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/authenticate")
//                .usernameParameter("user")
//                .passwordParameter("pwd")
//                .defaultSuccessUrl("/design", true)
                .and()
//                .oauth2Login()
//                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt())
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
