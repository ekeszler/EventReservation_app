package com.events.app.controllers;


import com.events.app.dtos.AuthRequestDTO;
import com.events.app.entities.User;
import com.events.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private UserService userService;



    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO authRequestDTO){
//        return ResponseEntity.ok(userService.authenticate(authRequestDTO));
//    }

    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(userService.register(authRequestDTO));
    }
}
