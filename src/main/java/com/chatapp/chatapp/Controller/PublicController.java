package com.chatapp.chatapp.Controller;

import com.chatapp.chatapp.Dataloader.Entity.Role;
import com.chatapp.chatapp.Dataloader.Entity.UserCred;
import com.chatapp.chatapp.Dataloader.service.RoleService;
import com.chatapp.chatapp.Dataloader.service.UserService;
import com.chatapp.chatapp.Utils.LoggerUtils.CaLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @GetMapping(value = "/getRoleById",produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getRole(@RequestParam Long id) {
        try {
            Role role = roleService.getRole(id);
            CaLogger.logs.info("Role name is {}", role.getName());
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping(value = "/saveRole",produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getRole(@RequestBody Role role) {
        try {
            Role role1 = roleService.saveRole(role);
            CaLogger.logs.info("Role id is {}", role1 .getName());
            return new ResponseEntity<>(role1, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/getUserById",produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUserCred(@RequestParam Long id) {
        try {
            UserCred userById = userService.getUserById(id);
            CaLogger.logs.info("UserCred name is {}", userById.getUsername());
            return new ResponseEntity<>(userById, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/getUserByName",produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUserCredByName(@RequestParam String name) {
        try {
            UserCred userById = userService.getUserByUsername(name);
            CaLogger.logs.info("UserCred fetched by name id is {}", userById.getId());
            return new ResponseEntity<>(userById, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/saveUser",produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveUserCred(@RequestBody UserCred userCred) {
        try {
            UserCred userById = userService.saveUser(userCred);
            CaLogger.logs.info("UserCred Id is {}", userById.getId());
            return new ResponseEntity<>(userById, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
