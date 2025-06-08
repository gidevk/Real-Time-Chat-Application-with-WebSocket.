package com.chatapp.chatapp.Dataloader.repository;

import com.chatapp.chatapp.Dataloader.Entity.UserCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCred, Long> {

    UserCred findByUsername(String username);
}
