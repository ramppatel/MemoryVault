package com.example.memoryVault.controller;

import com.example.memoryVault.entity.User;
import com.example.memoryVault.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "MemoryVault App is Working";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>("User Saved Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error while Saving User", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public void login(@RequestBody User user){

    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> findUser(@PathVariable String userName){
        try{
            User user = userService.findByUserName(userName);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error while Saving User", HttpStatus.NOT_FOUND);
        }

    }

}
