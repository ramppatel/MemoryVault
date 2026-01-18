package com.example.memoryVault.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;

@Configuration
public class MongoDebugConfig {

    @Autowired
    private MongoDatabaseFactory mongoDatabaseFactory;

    @PostConstruct
    public void printDatabaseName() {
        System.out.println("🔥 ACTUAL DATABASE IN USE = "
                + mongoDatabaseFactory.getMongoDatabase().getName());
    }
}
