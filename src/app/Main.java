package app;

import model.*;

public class Main {
    public static void main(String[] args){
        Workout workout = new Workout("Push Day");
        Exercise exercise = new Exercise("Bench Press");
        WorkoutSet set1 = new WorkoutSet(100, 10);
        WorkoutSet set2 = new WorkoutSet(150, 8);
        WorkoutSet set3 = new WorkoutSet(150, 5);

        exercise.addSet(set1);
        exercise.addSet(set2);
        exercise.addSet(set3);

        workout.addExercise(exercise);

        System.out.println(workout);
        System.out.println(workout.getDate());
        System.out.println();
        System.out.println(workout.getExercises());
        
        for(WorkoutSet set : exercise.getSets()){
            System.out.println(set);
        }

        System.out.println();

        System.out.println("Exercise Volume: " + exercise.getTotalVolume());
        System.out.println("Workout Volume: " + workout.getWorkoutVolume());

    }
}
