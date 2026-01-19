package com.example.memoryVault.service;

import com.example.memoryVault.entity.Goal;
import org.bson.types.ObjectId;

import java.util.List;

public interface GoalService {

    Goal createGoal(Goal goal);

    List<Goal> getMyGoals();

    Goal getGoalById(ObjectId goalId);

    boolean deleteGoal(ObjectId goalId);
}