package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

import model.Exercise;
import model.Workout;

public class WorkoutTracker {
    private List<Workout> workouts;
    
    public WorkoutTracker(){
        this.workouts = new ArrayList<>();
    }

    public void addWorkout(Workout workout){
        this.workouts.add(workout);
    }

    public boolean removeWorkout(int index){
        if(index < 0 || index >= workouts.size()) {
            return false;
        }

        workouts.remove(index);
        return true;
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
