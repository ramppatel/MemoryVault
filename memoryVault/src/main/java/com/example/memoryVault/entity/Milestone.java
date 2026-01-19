package com.example.memoryVault.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "milestones")
@Data
public class Milestone {

    @Id
    private ObjectId id;

    private ObjectId goalId;
    private ObjectId userId; // Owner

    private String title;
    private boolean completed;

    private LocalDateTime completedDate;
}