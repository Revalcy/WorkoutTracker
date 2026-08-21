package com.workouttracker.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workouttracker.model.Workout;
import com.workouttracker.service.WorkoutTracker;
import com.workouttracker.ui.Menu;
import com.workouttracker.storage.WorkoutStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ObjectMapper mapper = new ObjectMapper();
        Path filePath = Paths.get("workouts.json");
        WorkoutStorage storage = new WorkoutStorage(mapper, filePath);
        List<Workout> workouts = new ArrayList<>();


        try{
            workouts = storage.toList();
            System.out.println("Successfully loaded " + workouts.size() + " workouts.");
        } catch(IOException e){
            System.out.println("Could not load workouts: " + e.getMessage());
            System.out.println("Starting with an empty workout list.");
        }

        WorkoutTracker tracker = new WorkoutTracker(workouts, storage);
        Menu menu = new Menu(tracker);

        menu.start();

    }
}