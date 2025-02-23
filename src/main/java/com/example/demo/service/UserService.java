package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    public UserService(UserRepository repository){
        this.userRepository = repository;
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
}
