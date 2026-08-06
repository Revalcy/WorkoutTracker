package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Workout {
    private LocalDate date;
    private ArrayList<Exercise> exercises;
    private String workoutName;

    public Workout(String workoutName){
        this.workoutName = workoutName;
        this.date = LocalDate.now();
        this.exercises = new ArrayList<>();
    }

    public String getWorkoutName(){
        return workoutName;
    }

    public LocalDate getDate(){
        return date;
    }

    public ArrayList<Exercise> getExercises(){
        return exercises;
    }

    public void setWorkoutName(String workoutName){
        this.workoutName = workoutName;
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }

    public void removeExercise(int index){
        exercises.remove(index);
    }

    public double getWorkoutVolume(){
        double workoutVolume = 0;

        for(Exercise exercise : exercises){
            workoutVolume += exercise.getTotalVolume();
        }

        return workoutVolume;
    }

    @Override
    public String toString(){
        return workoutName;
    }
}