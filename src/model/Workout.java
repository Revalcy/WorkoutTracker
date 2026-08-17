package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Workout {
    private LocalDate date;
    private List<Exercise> exercises;
    private String workoutName;

    public Workout(String workoutName, LocalDate date){
        this.workoutName = workoutName;
        this.date = date;
        this.exercises = new ArrayList<>();
    }

    public Workout(String workoutName){
        this(workoutName, LocalDate.now());
    }

    public String getWorkoutName(){
        return workoutName;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public List<Exercise> getExercises(){
        return Collections.unmodifiableList(exercises);
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