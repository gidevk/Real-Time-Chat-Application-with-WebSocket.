package com.chatapp.chatapp.Dataloader.service;

import com.chatapp.chatapp.Dataloader.Entity.ApplicationStatus;

public interface ApplicationStatusService{

    ApplicationStatus save(ApplicationStatus applicationStatus);

    ApplicationStatus findByApplicationId(Integer id);
}
