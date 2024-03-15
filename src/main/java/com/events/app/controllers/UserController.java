package com.events.app.controllers;

import com.events.app.dtos.UserRequestDTO;
import com.events.app.entities.RoleType;
import com.events.app.entities.User;
import com.events.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addNewUser(userRequestDTO));
    }

    @PostMapping("/{roleType},/user/{userId}")
    public ResponseEntity<?> addRoleToUser(@PathVariable RoleType roleType, @PathVariable Long userId){
        return ResponseEntity.ok(userService.addRoleToUser(roleType,userId));
    }

//    @PostMapping("/{event},/user/{userId}")
//    public ResponseEntity<?> addEventToUser(@PathVariable Event event, @PathVariable Long userId){
//        return ResponseEntity.ok(userService.addEventToUser(event,userId));
//    }
}
