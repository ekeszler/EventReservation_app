package com.proiect_final.proiect_final.services;

import com.proiect_final.proiect_final.dtos.AuthRequestDTO;
import com.proiect_final.proiect_final.dtos.EventRequestDTO;
import com.proiect_final.proiect_final.dtos.UserRequestDTO;
import com.proiect_final.proiect_final.entities.Event;
import com.proiect_final.proiect_final.entities.Role;
import com.proiect_final.proiect_final.entities.RoleType;
import com.proiect_final.proiect_final.entities.User;
import com.proiect_final.proiect_final.exceptions.ResourceNotFoundException;
import com.proiect_final.proiect_final.repositories.EventRepository;
import com.proiect_final.proiect_final.repositories.RoleRepository;
import com.proiect_final.proiect_final.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    EventRepository eventRepository;
    RoleRepository roleRepository;
    EventService eventService;

    //private AuthenticationManager authenticationManager;

    //private JWTTokenService jwtTokenService;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, EventRepository eventRepository, RoleRepository roleRepository,  UserDetailsServiceImpl userDetailsService, EventService eventService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.roleRepository = roleRepository;
        this.userDetailsService = userDetailsService;
        this.eventService = eventService;
    }

    @Transactional
    public User addNewUser(UserRequestDTO userRequestDTO) {

        User user1 = new User(userRequestDTO.getName());
        RoleType roleType1 = userRequestDTO.getRoleType();
        Role role1 = roleRepository.findByRoleType(roleType1).orElseThrow(() -> new ResourceNotFoundException("role not found"));
        role1.getUsers().add(user1);
        user1.getRoles().add(role1);
        return userRepository.save(user1);
    }

    @Transactional
    public Role addRoleToUser(RoleType roleType, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Role role1 = roleRepository.findByRoleType(roleType).orElseThrow(() -> new ResourceNotFoundException("role not found"));
        role1.getUsers().add(user);
        user.getRoles().add(role1);
        return roleRepository.save(role1);
    }

    @Transactional
    public Event addEventToUser(Event event, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.getEvent().add(event);
        return eventRepository.save(event);
    }



//    public String authenticate(AuthRequestDTO authRequestDTO) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
//        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
//        return jwtTokenService.generateToken(userDetails);
//    }


}
