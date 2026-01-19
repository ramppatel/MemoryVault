package com.example.memoryVault.controller;

import com.example.memoryVault.entity.Goal;
import com.example.memoryVault.service.GoalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.createGoal(goal);
    }

    @GetMapping("/my")
    public List<Goal> getMyGoals() {
        return goalService.getMyGoals();
    }

    @GetMapping("/{id}")
    public Goal getGoal(@PathVariable ObjectId id) {
        return goalService.getGoalById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable ObjectId id) {
        try {
            boolean deleted = goalService.deleteGoal(id);
            if(deleted) return new ResponseEntity<>("Goal Deleted Successfully !!!", HttpStatus.OK);
            return new ResponseEntity<>("Goal Not Found !!!", HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

