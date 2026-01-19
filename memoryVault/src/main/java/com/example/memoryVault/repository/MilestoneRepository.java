package com.example.memoryVault.repository;

import com.example.memoryVault.entity.Milestone;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MilestoneRepository extends MongoRepository<Milestone, ObjectId> {

    List<Milestone> findAllByGoalId(ObjectId goalId);

    long countByGoalIdAndCompletedFalse(ObjectId goalId);
}
