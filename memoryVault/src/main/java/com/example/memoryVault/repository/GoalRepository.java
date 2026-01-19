package com.example.memoryVault.repository;

import com.example.memoryVault.entity.Goal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends MongoRepository<Goal, ObjectId> {

    List<Goal> findAllByUserId(ObjectId userId);

    Optional<Goal> findByIdAndUserId(ObjectId id, ObjectId userId);
}
