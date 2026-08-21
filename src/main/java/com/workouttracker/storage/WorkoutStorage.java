package com.workouttracker.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import com.workouttracker.model.Workout;

public class WorkoutStorage {
    private ObjectMapper mapper;
    private Path filePath;
    

    public WorkoutStorage(ObjectMapper mapper, Path filePath){
        this.mapper = mapper;
        this.filePath = filePath;
        
        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

    }

    public String toJson(List<Workout> workouts)throws IOException{
        String jsonString = mapper.writeValueAsString(workouts);
        return jsonString;
    }

    public void saveJson(List<Workout> workouts)throws IOException{
        String jsonString = toJson(workouts);

        Files.writeString(filePath, jsonString);
    }

    public List<Workout> toList()throws IOException{
        String content = Files.readString(filePath);

        List<Workout> workouts = mapper.readValue(content, new TypeReference<List<Workout>>() {});

        return workouts;
    }
}
