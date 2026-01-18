package com.example.memoryVault.entity;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;

    private String userName;

    @Indexed(unique = true)
    private String email;
    @Nonnull
    private String password;

    private List<String> roles;

    private LocalDateTime createdAt;
}
