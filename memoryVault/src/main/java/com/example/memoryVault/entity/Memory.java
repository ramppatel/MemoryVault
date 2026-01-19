package com.example.memoryVault.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "memories")
@Data
public class Memory {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private List<String> tags;
    private String mood;
    private ObjectId userId; // Owner of the Memory
    private LocalDateTime createdAt;
}

