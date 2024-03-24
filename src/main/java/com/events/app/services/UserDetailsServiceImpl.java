package com.events.app.services;


import com.events.app.entities.User;
import com.events.app.exceptions.ResourceNotFoundException;
import com.events.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    private UserService userService;



    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;

    }




    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(userService.getLoggedInUsername()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return new org.springframework.security.core.userdetails.User (user.getUserName(), user.getPassword(), buildSimpleGrantedAuthorities(user));
    }

    @Transactional
    public List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(User user){
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
                .collect(Collectors.toList());
    }


}
