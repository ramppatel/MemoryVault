package com.example.memoryVault.service;

import com.example.memoryVault.entity.Milestone;
import org.bson.types.ObjectId;

import java.util.List;

public interface MilestoneService {

    Milestone addMilestone(ObjectId goalId, Milestone milestone);

    Milestone completeMilestone(ObjectId milestoneId);

    List<Milestone> getMilestonesByGoal(ObjectId goalId);
}
