package com.chatapp.chatapp.Dataloader.service;

import com.chatapp.chatapp.Dataloader.Entity.ApplicationStatus;
import com.chatapp.chatapp.Dataloader.repository.ApplicationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {

    @Autowired
    private ApplicationStatusRepository applicationStatusRepository;

    @Override
    public ApplicationStatus save(ApplicationStatus applicationStatus){
        return applicationStatusRepository.save(applicationStatus);
    }

    @Override
    public ApplicationStatus findByApplicationId(Integer id){
        return applicationStatusRepository.findById(id).orElse(null);
    }
}
