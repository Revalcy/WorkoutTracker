package com.workouttracker.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.workouttracker.model.Exercise;
import com.workouttracker.model.Workout;
import com.workouttracker.storage.WorkoutStorage;

import java.util.Collections;
import java.util.HashMap;

public class WorkoutTracker {
    private List<Workout> workouts;
    private WorkoutStorage storage;
    
    public WorkoutTracker(){
        this.workouts = new ArrayList<>();
    }

    public WorkoutTracker(List<Workout> workouts, WorkoutStorage storage){
        this.workouts = new ArrayList<>(workouts);
        this.storage = storage;
    }

    public void addWorkout(Workout workout){
        try{
            this.workouts.add(workout);
            storage.saveJson(workouts);
        } catch(IOException e){
            this.workouts.remove(workout);
            System.out.println("Failed to save workout: " + e.getMessage());
        }
    }

    public boolean removeWorkout(int index){
        if(index < 0 || index >= workouts.size()) {
            return false;
        }

        Workout removedWorkout = workouts.get(index);
        workouts.remove(index);

        try{
            storage.saveJson(workouts);
            return true;
        } catch(IOException e){
            workouts.add(index, removedWorkout);
            System.out.println("Failed to update save file: " + e.getMessage());
            return false;
        }
    }

    public Workout getWorkout(int index){
        if(index < 0 || index >= workouts.size()){
            return null;
        }

        return workouts.get(index);
    }

    public List<Workout> getWorkouts(){
        return Collections.unmodifiableList(workouts);
    }

    public int getWorkoutCount(){
        return workouts.size();
    }

    public List<Workout> searchByExercise(String exerciseName){
        List<Workout> results = new ArrayList<>();

        for(Workout workout : workouts){
            for(Exercise exercise : workout.getExercises()){
                if(exercise.getName().equals(exerciseName)){
                    results.add(workout);
                    break;
                }
            }
        }

        return results;
    }

    public List<Workout> searchByDate(LocalDate date){
        List<Workout> results = new ArrayList<>();

        for(Workout workout : workouts){
            if(workout.getDate().equals(date)){
                results.add(workout);
            }
        }

        return results;
    }

    public int getTotalExercises(){
        int totalExercises = 0;

        for(Workout workout : workouts){
            totalExercises += workout.getExercises().size();
        }

        return totalExercises;
    }

    public int getTotalSets(){
        int totalSets = 0;

        for(Workout workout : workouts){
            for(Exercise exercise : workout.getExercises()){
                totalSets += exercise.getSets().size();
            }
        }

        return totalSets;
    }

    public double getTotalVolume(){
        double totalVolume = 0;

        for(Workout workout : workouts){
            totalVolume += workout.getWorkoutVolume();
        }

        return totalVolume;
    }

    public double getAverageWorkoutVolume(){
        double totalAverage = getTotalVolume();
        int count = getWorkoutCount();

        if(count == 0){
            return 0;
        }

        return totalAverage / count;
    }

    public String getMostPerformedExercise(){
        HashMap<String, Integer> map = new HashMap<>();
        int highestCount = -1;
        String performedName = null;

        for(Workout workout : workouts){
            for(Exercise exercise : workout.getExercises()){
                map.put(exercise.getName(),
                map.getOrDefault(exercise.getName(), 0) + exercise.getSets().size());
            }
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() > highestCount){
                highestCount = entry.getValue();
                performedName = entry.getKey();
            }
        }

        return performedName;
    }
}
