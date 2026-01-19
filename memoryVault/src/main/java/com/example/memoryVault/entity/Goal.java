package com.example.memoryVault.entity;

import com.example.memoryVault.enums.GoalStatus;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "goals")
@Data
public class Goal {

    @Id
    private ObjectId id;

    private String title;
    private String description;

    private ObjectId userId; // Owner
    private GoalStatus status;

    private LocalDateTime createdDate;
}
