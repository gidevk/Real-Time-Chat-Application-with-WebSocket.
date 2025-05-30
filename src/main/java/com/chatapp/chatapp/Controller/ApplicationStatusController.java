package com.chatapp.chatapp.Controller;

import com.chatapp.chatapp.Dataloader.Entity.ApplicationStatus;
import com.chatapp.chatapp.Dataloader.repository.ApplicationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/application")
public class ApplicationStatusController {

    @Autowired
    private ApplicationStatusRepository applicationStatusRepository;

    @PostMapping(path = "/save", produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody ApplicationStatus applicationStatus){
        ApplicationStatus result=new ApplicationStatus();
        try {
            applicationStatus.setCreated(LocalDateTime.now());
            applicationStatus.setUpdated(LocalDateTime.now());

            result=applicationStatusRepository.save(applicationStatus);
            System.out.println("save application status "+result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
