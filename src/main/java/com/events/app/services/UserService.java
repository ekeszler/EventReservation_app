package com.events.app.services;

import com.events.app.dtos.AuthRequestDTO;
import com.events.app.dtos.UserRequestDTO;
import com.events.app.entities.Role;
import com.events.app.entities.RoleType;
import com.events.app.entities.User;
import com.events.app.exceptions.ResourceNotFoundException;
import com.events.app.repositories.EventRepository;
import com.events.app.repositories.RoleRepository;
import com.events.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    EventRepository eventRepository;
    RoleRepository roleRepository;
    EventService eventService;

    private AuthenticationManager authenticationManager;

    private JWTTokenService jwtTokenService;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, EventRepository eventRepository, RoleRepository roleRepository,   EventService eventService) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.roleRepository = roleRepository;

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


    public String authenticate(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }





}
