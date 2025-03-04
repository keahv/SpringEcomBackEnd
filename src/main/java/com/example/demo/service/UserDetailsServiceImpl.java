package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserPrincipal;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(username);
        if (user == null) {
            System.out.println("User not fount");
            throw new UsernameNotFoundException("user not found");
        }
        user.setUserName(user.getUserEmail());// replace name with email
        return new UserPrincipal(user);
    }
}
