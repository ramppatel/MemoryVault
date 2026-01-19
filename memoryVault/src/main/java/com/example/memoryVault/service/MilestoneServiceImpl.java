package com.example.memoryVault.service;

import com.example.memoryVault.entity.Goal;
import com.example.memoryVault.entity.Milestone;
import com.example.memoryVault.entity.User;
import com.example.memoryVault.enums.GoalStatus;
import com.example.memoryVault.repository.GoalRepository;
import com.example.memoryVault.repository.MilestoneRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MilestoneServiceImpl implements MilestoneService {

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserService userService;

    @Override
    public Milestone addMilestone(ObjectId goalId, Milestone milestone) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        Goal goal = goalRepository.findByIdAndUserId(goalId, user.getId())
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        milestone.setGoalId(goalId);
        milestone.setUserId(user.getId());
        milestone.setCompleted(false);

        return milestoneRepository.save(milestone);
    }

    @Override
    public Milestone completeMilestone(ObjectId milestoneId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));

        if (!milestone.getUserId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        milestone.setCompleted(true);
        milestone.setCompletedDate(LocalDateTime.now());
        milestoneRepository.save(milestone);

        long remaining = milestoneRepository.countByGoalIdAndCompletedFalse(milestone.getGoalId());

        if (remaining == 0) {
            Goal goal = goalRepository.findById(milestone.getGoalId()).get();
            goal.setStatus(GoalStatus.COMPLETED);
            goalRepository.save(goal);
        }

        return milestone;
    }

    @Override
    public List<Milestone> getMilestonesByGoal(ObjectId goalId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUser(userName);

        goalRepository.findByIdAndUserId(goalId, user.getId())
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        return milestoneRepository.findAllByGoalId(goalId);
    }
}

