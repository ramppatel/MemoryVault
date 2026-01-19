package com.example.memoryVault.controller;

import com.example.memoryVault.entity.Milestone;
import com.example.memoryVault.service.MilestoneService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MilestoneController {

    @Autowired
    private MilestoneService milestoneService;

    @PostMapping("/goals/{goalId}/milestones")
    public Milestone addMilestone(
            @PathVariable ObjectId goalId,
            @RequestBody Milestone milestone) {

        return milestoneService.addMilestone(goalId, milestone);
    }

    @PutMapping("/milestones/{id}/complete")
    public Milestone completeMilestone(@PathVariable ObjectId id) {
        return milestoneService.completeMilestone(id);
    }

    @GetMapping("/goals/{goalId}/milestones")
    public List<Milestone> getMilestones(@PathVariable ObjectId goalId) {
        return milestoneService.getMilestonesByGoal(goalId);
    }
}

