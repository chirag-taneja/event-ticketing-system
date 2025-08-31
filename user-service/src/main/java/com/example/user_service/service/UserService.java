package com.example.user_service.service;

import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user){
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent())
        {
            return byUsername;
        }
        else {
            log.error("User Not Found with this user name :: ",username);
            throw new RuntimeException("User Not Found with this user name");
        }
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent())
        {
            return byId;
        }
        else {
            log.error("User Not Found with this user name :: ",id);
            throw new RuntimeException("User Not Found with this user id "+id);
        }
    }
}
