package com.example.memoryVault.service;

import com.example.memoryVault.entity.Goal;
import com.example.memoryVault.entity.User;
import com.example.memoryVault.enums.GoalStatus;
import com.example.memoryVault.repository.GoalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserService userService;

    @Override
    public Goal createGoal(Goal goal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        goal.setUserId(user.getId());
        goal.setStatus(GoalStatus.ACTIVE);
        goal.setCreatedDate(LocalDateTime.now());
        return goalRepository.save(goal);
    }

    @Override
    public List<Goal> getMyGoals() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return goalRepository.findAllByUserId(user.getId());
    }

    @Override
    public Goal getGoalById(ObjectId goalId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return goalRepository.findByIdAndUserId(goalId, user.getId())
                .orElseThrow(() -> new RuntimeException("Goal not found"));
    }

    @Override
    public boolean deleteGoal(ObjectId goalId) {
        Goal goal = getGoalById(goalId);
        if(goal == null) return false;
        goalRepository.delete(goal);
        return true;
    }
}

