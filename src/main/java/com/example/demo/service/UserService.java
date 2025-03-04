package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private  JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository){
        this.userRepository = repository;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUserName());
        } else {
            return "fail";
        }
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public void deleteUserById(int id){
         userRepository.deleteById(id);
    }


    public boolean isValidEmail(String email) {
        long count = getAllUsers().stream()
                .filter(user -> user.getUserEmail().equals(email))
                .count();
        return count > 0 ? false : true;
    }

    public User findUserByEmail(String email){
        return userRepository.findByUserEmail(email);
    }
}
