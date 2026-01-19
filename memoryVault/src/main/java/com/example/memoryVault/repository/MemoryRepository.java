package com.example.memoryVault.repository;

import com.example.memoryVault.entity.Memory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MemoryRepository extends MongoRepository<Memory, ObjectId> {
    Optional<Memory> findByIdAndUserId(ObjectId id, ObjectId userId);
    void deleteById(ObjectId id);
    List<Memory> findAllByUserId(ObjectId userId);
    Optional<Memory> findById(ObjectId id);
}
