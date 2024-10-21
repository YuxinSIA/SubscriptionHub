package org.yuxinwu.subscriptionhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yuxinwu.subscriptionhub.model.User;
import org.yuxinwu.subscriptionhub.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        logger.debug("Attempting to register user with email: {}", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        User savedUser = userRepository.save(user);
        logger.info("User successfully registered with email: {}", savedUser.getEmail());
        return savedUser;
    }

    public User findUserByEmail(String email) {
        logger.debug("Searching for user by email: {}", email);
        User foundUser = userRepository.findByEmail(email).orElse(null);
        if (foundUser != null) {
            logger.info("User found: {}", foundUser.getEmail());
        } else {
            logger.warn("No user found with email: {}", email);
        }
        return foundUser;
    }

    public Optional<User> findUserById(Long id) {
        logger.debug("Searching for user by ID: {}", id);
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            logger.info("User found with ID: {}", id);
        } else {
            logger.warn("No user found with ID: {}", id);
        }
        return foundUser;
    }
}


