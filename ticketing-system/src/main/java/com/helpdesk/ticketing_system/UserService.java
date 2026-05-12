package com.helpdesk.ticketing_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

    public User registerUser(String email, String password, String role) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }
       

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return null;
        }

        return user;
    }
}
