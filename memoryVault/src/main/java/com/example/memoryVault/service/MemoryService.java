package com.example.memoryVault.service;

import com.example.memoryVault.entity.Memory;
import com.example.memoryVault.repository.MemoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemoryService {
    @Autowired
    private MemoryRepository memoryRepository;

    public void saveMemory(Memory memory, ObjectId userId){
        memory.setUserId(userId);
        memory.setCreatedAt(LocalDateTime.now());
        memoryRepository.save(memory);
    }

    public List<Memory> getAllMyEntries(ObjectId userId){
        return memoryRepository.findAllByUserId(userId);
    }

    public Optional<Memory> getMemoryById(ObjectId id, ObjectId userId){
        return memoryRepository.findByIdAndUserId(id, userId);
    }

    public boolean deleteMemoryById(ObjectId memoryId, ObjectId userId) {
        Optional<Memory> memoryOpt = memoryRepository.findByIdAndUserId(memoryId, userId);

        if (memoryOpt.isPresent()) {
            memoryRepository.delete(memoryOpt.get());
            return true;
        }
        return false;
    }

    public Optional<Memory> updateMemoryById(ObjectId memoryId, ObjectId userId, Memory updatedMemory) {
        Optional<Memory> existingOpt = memoryRepository.findByIdAndUserId(memoryId, userId);

        if (existingOpt.isPresent()) {
            Memory existing = existingOpt.get();

            // Update only allowed fields
            existing.setTitle(updatedMemory.getTitle());
            existing.setContent(updatedMemory.getContent());
            existing.setTags(updatedMemory.getTags());
            existing.setMood(updatedMemory.getMood());

            // Do NOT update id, userId, createdAt
            return Optional.of(memoryRepository.save(existing));
        }

        return Optional.empty();
    }

}
