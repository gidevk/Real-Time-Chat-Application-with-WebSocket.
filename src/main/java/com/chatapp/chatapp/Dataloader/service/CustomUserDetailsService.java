package com.chatapp.chatapp.Dataloader.service;

import com.chatapp.chatapp.Dataloader.Entity.Role;
import com.chatapp.chatapp.Dataloader.Entity.UserCred;
import com.chatapp.chatapp.Dataloader.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


//This calss for spring security config
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Load user from DB by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCred user = userRepository.findByUsername(username);

        if (user != null) {

            List<String> roleNames = user.getRoles().stream()
                    .map(Role::getName)
                    .toList();

            //            CaLogger.logs.info("build value {}",build);
            return User.builder()
                    .username(user.getUsername())
                    .password((user.getPassword()))
                    .roles(String.valueOf(roleNames))
                    .build();
        }
        return null;
    }
}