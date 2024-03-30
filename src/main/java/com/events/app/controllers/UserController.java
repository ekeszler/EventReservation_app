package com.events.app.controllers;

import com.events.app.dtos.EventRequestDTO;
import com.events.app.dtos.PackageRequestDTO;
import com.events.app.dtos.UserRequestDTO;
import com.events.app.entities.Product;
import com.events.app.entities.RoleType;
import com.events.app.entities.User;
import com.events.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{roleType},/user/{userName}")
    public ResponseEntity<?> addRoleToUser(@PathVariable RoleType roleType, @PathVariable String userName){
        return ResponseEntity.ok(userService.addRoleToUser(roleType));
    }

    @PostMapping("/addEvent")
    public ResponseEntity<?> addEventToUser(@RequestBody EventRequestDTO eventRequestDTO){
        return ResponseEntity.ok(userService.addEventToUser(eventRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        try{
            userService.deleteUser(userId);
            return ResponseEntity.ok("The user with id " + userId + " was deleted");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, user was not deleted");
        }
    }
}
