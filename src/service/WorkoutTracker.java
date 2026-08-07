package service;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Exercise;
import model.Workout;

public class WorkoutTracker {
    private ArrayList<Workout> workouts;
    
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

    public ArrayList<Workout> getWorkouts(){
        return workouts;
    }

    public int getWorkoutCount(){
        return workouts.size();
    }

    public ArrayList<Workout> searchByExercise(String exerciseName){
        ArrayList<Workout> results = new ArrayList<>();

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

    public Workout searchByDate(LocalDate date){
        for(Workout workout : workouts){
            if(workout.getDate().equals(date)){
                return workout;
            }
        }

        return null;
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
}
