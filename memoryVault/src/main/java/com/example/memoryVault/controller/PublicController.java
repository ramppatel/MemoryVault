package com.example.memoryVault.controller;

import com.example.memoryVault.entity.User;
import com.example.memoryVault.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/health-check")
    public String healthCheck(){
        return "MemoryVault App is Working";
    }

    @Autowired
    private UserService userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@RequestBody User user){
        try{
            String oldPlainPassword = user.getPassword();
            String encodedPassword = passwordEncoder.encode(oldPlainPassword);
            user.setPassword(encodedPassword);
            user.setRoles(Arrays.asList("USER"));
            user.setCreatedAt(LocalDateTime.now());
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
