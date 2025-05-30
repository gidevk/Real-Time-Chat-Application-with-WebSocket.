package com.chatapp.chatapp.Dataloader.repository;

import com.chatapp.chatapp.Dataloader.Entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Integer> {

}
