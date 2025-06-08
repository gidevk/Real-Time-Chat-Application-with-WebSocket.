package com.chatapp.chatapp.Dataloader.service;

import com.chatapp.chatapp.Dataloader.Entity.Role;

public interface RoleService {

    Role getRole(Long id);

    Role saveRole(Role role);
}
