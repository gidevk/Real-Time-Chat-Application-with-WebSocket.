package com.chatapp.chatapp.Dataloader.service;

import com.chatapp.chatapp.Dataloader.Entity.UserCred;

public interface UserService {

    UserCred saveUser(UserCred user);

    UserCred getUserById(Long id);

    UserCred getUserByUsername(String userName);
}
