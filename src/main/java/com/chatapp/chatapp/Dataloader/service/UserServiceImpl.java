package com.chatapp.chatapp.Dataloader.service;

import com.chatapp.chatapp.Dataloader.Entity.Role;
import com.chatapp.chatapp.Dataloader.Entity.UserCred;
import com.chatapp.chatapp.Dataloader.repository.RoleRepository;
import com.chatapp.chatapp.Dataloader.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserCred saveUser(UserCred user){

        // Check if the username already exists
        UserCred byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername != null && byUsername.getUsername().equals(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }

        // Fetch roles from DB and populate roleEntities set
        Set<Role> roleEntities = user.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        UserCred user1 = new UserCred();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRoles(roleEntities);

        return userRepository.save(user1);
    }

    @Override
    public UserCred getUserById(Long id){
        return userRepository.findById(id).get();
    }

    @Override
    public UserCred getUserByUsername(String userName){
        return userRepository.findByUsername(userName);
    }
}