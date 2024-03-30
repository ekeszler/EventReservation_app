package com.events.app.services;

import com.events.app.dtos.AuthRequestDTO;
import com.events.app.dtos.EventRequestDTO;
import com.events.app.dtos.PackageRequestDTO;
import com.events.app.dtos.UserRequestDTO;
import com.events.app.entities.*;
import com.events.app.entities.Package;
import com.events.app.exceptions.ResourceNotFoundException;
import com.events.app.mapper.UserMapper;
import com.events.app.repositories.EventRepository;
import com.events.app.repositories.RoleRepository;
import com.events.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    EventRepository eventRepository;
    RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    private AuthenticationManager authenticationManager;

    private JWTTokenService jwtTokenService;

    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UserService(UserRepository userRepository, EventRepository eventRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTTokenService jwtTokenService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;

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
    public Role addRoleToUser(RoleType roleType) {
        User user = userRepository.findUserByUserName(userMapper.getLoggedInUsername()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Role role1 = roleRepository.findByRoleType(roleType).orElseThrow(() -> new ResourceNotFoundException("role not found"));
        role1.getUsers().add(user);
        user.getRoles().add(role1);
        return roleRepository.save(role1);
    }

    @Transactional
    public Event addEventToUser(EventRequestDTO eventRequestDTO) {
        User user = userRepository.findById(eventRequestDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Event event = eventRepository.findByName(eventRequestDTO.getName()).orElseThrow(() -> new ResourceNotFoundException("event not found"));
        event.setUser(user);
        return eventRepository.save(event);
    }

    @Transactional
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        userRepository.delete(user);
    }


    public String authenticate(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequestDTO.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }

    @Transactional
    public User register (AuthRequestDTO authRequestDTO){
        Optional<User> userOptional = userRepository.findUserByUserName(authRequestDTO.getUsername());
        if (userOptional.isPresent()){
            throw new ResourceNotFoundException("already exist");
        }
        User user = new User();
        user.setUserName(authRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER).orElseThrow(()->new ResourceNotFoundException("role not found"));
        user.getRoles().add(role);
        role.getUsers().add(user);
        return userRepository.save(user);
    }


}
