package com.events.app.mapper;

import com.events.app.dtos.UserRequestDTO;
import com.events.app.entities.User;
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

}
