package com.events.app.mapper;

import com.events.app.dtos.UserRequestDTO;
import com.events.app.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private List<User> loggedInUser = new ArrayList<>();

    public User mapFromDTO(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setUserName(userRequestDTO.getUserName());
        user.setEmail(userRequestDTO.getEmail());
        return user;
    }

    //TODO de mutat in userService
    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        } else {
            // Utilizatorul nu este autentificat
            return null;
        }
    }

}
