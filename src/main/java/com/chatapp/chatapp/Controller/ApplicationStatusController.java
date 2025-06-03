package com.chatapp.chatapp.Controller;

import com.chatapp.chatapp.Dataloader.Entity.ApplicationStatus;
import com.chatapp.chatapp.Dataloader.repository.ApplicationStatusRepository;
import com.chatapp.chatapp.Utils.EmailService.EmailSenderUtils;
import com.chatapp.chatapp.Utils.LoggerUtils.CaLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/application")
public class ApplicationStatusController {

    @Autowired
    private ApplicationStatusRepository applicationStatusRepository;
    @Autowired
    EmailSenderUtils emailSenderUtils;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(path = "/save", produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestBody ApplicationStatus applicationStatus){
        ApplicationStatus result=new ApplicationStatus();
        try {
            applicationStatus.setCreated(LocalDateTime.now());
            applicationStatus.setUpdated(LocalDateTime.now());

            result=applicationStatusRepository.save(applicationStatus);
            CaLogger.logs.info("save application status {}",result);
//            emailSenderUtils.sendWelcomeEmail("idJee0001@gmail.com","Ramesh kumar","www.google.co.in");
//            emailSenderUtils.sendWelcomeEmail(result.getType(),result.getName(),"www.google.co.in");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            CaLogger.logs.error("save application status ",e);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(path = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> get(@RequestParam Integer apId){
        ApplicationStatus status= new ApplicationStatus();

        try {
            CaLogger.logs.info("Get ApplicationStatus started");
            status= applicationStatusRepository.getApplicationStatusByApplicationId(apId);

            CaLogger.logs.info("get application status {}",status.getStatus());
            /*for (int i = 0; i < 10; i++) {
                CaLogger.logs.info("Getting ApplicationStatus Details by using {}",apId);
                CaLogger.logs.debug("Getting ApplicationStatus Details by using {}",apId);
                CaLogger.logs.error("Getting ApplicationStatus Details by using {}",apId);
                CaLogger.logs.warn("Getting ApplicationStatus Details by using {}",apId);
                CaLogger.logs.trace("Getting ApplicationStatus Details by using {}",apId);
                System.out.println("Fetching done!");
                CaLogger.redirectSystemOutToLogger();
            }*/
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            CaLogger.logs.error("get application status ",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
