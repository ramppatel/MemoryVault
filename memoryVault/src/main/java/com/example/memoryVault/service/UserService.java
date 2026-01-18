package com.example.memoryVault.service;

import com.example.memoryVault.entity.User;
import com.example.memoryVault.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getUser(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteUser(String userName){
        userRepository.deleteByUserName(userName);
    }

}
