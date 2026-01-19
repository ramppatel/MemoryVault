package com.example.memoryVault.controller;

import com.example.memoryVault.entity.Memory;
import com.example.memoryVault.entity.User;
import com.example.memoryVault.service.MemoryService;
import com.example.memoryVault.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/memories")
public class MemoryController {
    @Autowired
    private MemoryService memoryService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addMemory(@RequestBody Memory memory){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.getUser(userName);
            memoryService.saveMemory(memory, user.getId());
            return new ResponseEntity<>(memory, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Something Went Wrong !!!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/my")
    public ResponseEntity<?> getAllMyMemories(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.getUser(userName);
            List<Memory> allMemories = memoryService.getAllMyEntries(user.getId());
            return new ResponseEntity<>(allMemories, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Something Went Wrong !!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemory(@PathVariable ObjectId id){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userService.getUser(userName);
            Optional<Memory> memory = memoryService.getMemoryById(id, user.getId());
            if(memory.isPresent()){
                return new ResponseEntity<>(memory.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>("Please Enter Valid Memory Id", HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>("Something Went Wrong !!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMemory(@PathVariable ObjectId id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            User user = userService.getUser(userName);

            boolean deleted = memoryService.deleteMemoryById(id, user.getId());

            if (deleted) {
                return new ResponseEntity<>("Memory deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Memory not found or not authorized", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Something Went Wrong !!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
