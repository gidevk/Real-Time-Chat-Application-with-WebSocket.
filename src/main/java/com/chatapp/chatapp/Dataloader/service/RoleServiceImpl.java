package com.chatapp.chatapp.Dataloader.service;

import com.chatapp.chatapp.Dataloader.Entity.Role;
import com.chatapp.chatapp.Dataloader.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRole(Long id){
        return roleRepository.getRoleById(id);
    }

    @Override
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
}
