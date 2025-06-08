package com.chatapp.chatapp.Dataloader.repository;

import com.chatapp.chatapp.Dataloader.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Integer> {

    Role getRoleById(Long id);

    Optional<Role> findByName(String roleName);
}
