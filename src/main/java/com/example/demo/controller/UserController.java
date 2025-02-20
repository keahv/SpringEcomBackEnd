package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Stream;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService service){
        this.userService = service;
    }

    @RequestMapping("/")
    public String greetUser(){
        return "Hello User";
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            User user1 = userService.addUser(user);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        User user = userService.getUserById(id);
        Map<String,Object> resp = new HashMap<>();
        if (user != null){
            resp.put("msg","User Found");
            resp.put("status",true);
            return new ResponseEntity<>(resp,HttpStatus.FOUND);
        }
        resp.put("msg","Record Not Found please Sign Up");
        resp.put("status",false);
        return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody() User requestedUser){
       List<User> users = userService.getAllUsers();
       Map<String,Object> resp = new HashMap<>();
       for (User user:users){
           if (user.getUserEmail().equals(requestedUser.getUserEmail()) && user.getPassword().equals(requestedUser.getPassword())) {
               resp.put("msg","Login Successful");
               resp.put("status",true);
               resp.put("data",user);
               return new ResponseEntity<>(resp,HttpStatus.OK);
           }
       }
       resp.put("msg","Wrong Credentials");
       resp.put("status",false);
       return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User updatedUser = userService.addUser(user);
        if (updatedUser != null){
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        Map<String,String> resp = new HashMap<>();
        resp.put("msg","Failed to Update User");
        return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        Map<String,Object> resp = new HashMap<>();
            User user = userService.getUserById(id);
            if (user != null) {
            userService.deleteUserById(id);
            resp.put("msg", "User Removed Successfully");
            resp.put("status", HttpStatus.OK);
            return new ResponseEntity<>(resp,HttpStatus.OK);
            }
            resp.put("msg", "Failed to Update User");
            resp.put("status", HttpStatus.EXPECTATION_FAILED);
            return new ResponseEntity<>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
