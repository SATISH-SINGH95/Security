package com.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.model.entity.User;
import com.security.model.request.UserRequest;
import com.security.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRequest userRequest) {
        User user = User.builder().username(userRequest.getUsername())
            .password(passwordEncoder.encode(userRequest.getPassword()))
            .roles("USER")
            .build();

        userRepository.save(user);
    }

}
