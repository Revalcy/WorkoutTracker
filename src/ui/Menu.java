package ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

import service.WorkoutTracker;
import model.Exercise;
import model.Workout;
import model.WorkoutSet;

public class Menu {
    private Scanner scanner;
    private WorkoutTracker tracker;

    public Menu(WorkoutTracker tracker){
        scanner = new Scanner(System.in);
        this.tracker = tracker;
    }

    public void start(){
        while(true){
            displayMenu();

            int choice = Integer.valueOf(scanner.nextLine());

            switch(choice){
                case 1 :
                    addWorkout();
                    break;
                case 2 :
                    viewWorkouts();
                    break;
                case 3 :
                    deleteWorkout();
                    break;
                case 4 :
                    searchWorkout();
                    break;
                case 5 :
                    showStatistics();
                    break;
                case 6 :
                    System.out.println("Good bye");
                    return;
                default :
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    public void displayMenu(){
        System.out.println("\nWorkout Tracker");
        System.out.println("----------------");
        System.out.println("1. Add Workout");
        System.out.println("2. View Workouts");
        System.out.println("3. Delete Workout");
        System.out.println("4. Search Workout");
        System.out.println("5. View Statistics");
        System.out.println("6. Exit");
        System.out.print("Choice: ");
    }

    public void addWorkout(){
        System.out.println("Enter workout name: ");
        String workoutName = scanner.nextLine();

        Workout workout = new Workout(workoutName);

        System.out.println("How many exercises? ");
        int exerciseCount = Integer.valueOf(scanner.nextLine());

        for(int i = 0; i < exerciseCount; i++){
            System.out.println("Enter exercise name: ");
            String exerciseName = scanner.nextLine();

            Exercise exercise = new Exercise(exerciseName);

            System.out.println("How many sets? ");
            int setCount = Integer.valueOf(scanner.nextLine());

            for(int j = 0; j < setCount; j++){
                System.out.println("Set " + (j + 1));

                System.out.println("Weight: ");
                double weight = Double.valueOf(scanner.nextLine());

                System.out.println("Reps: ");
                int reps = Integer.valueOf(scanner.nextLine());

                WorkoutSet set = new WorkoutSet(weight, reps);

                exercise.addSet(set);
            }

            workout.addExercise(exercise);
        }
        tracker.addWorkout(workout);

        System.out.println("Workout added successfully!");
    }

    public void viewWorkouts(){
        if(tracker.getWorkoutCount() == 0){
            System.out.println("No workouts found.");
            return;
        }

        for(Workout workout : tracker.getWorkouts()){
            System.out.println("\nWorkout: " + workout.getWorkoutName());
            System.out.println("Date: " + workout.getDate());

            System.out.println("Exercises: ");

            for(Exercise exercise : workout.getExercises()){
                System.out.println("- " + exercise.getName());

                System.out.println(" Sets:");

                for(WorkoutSet set : exercise.getSets()){
                    System.out.println(
                        "   " + set.getWeight() + 
                        " lbs x " + set.getReps() + " reps"
                    );
                }
            }

            System.out.println("Total Volume: " + workout.getWorkoutVolume());
            System.out.println("----------------------");
        }
    }

    public void deleteWorkout(){
        if(tracker.getWorkoutCount() == 0){
            System.out.println("No workouts available.");
            return;
        }

        System.out.println("Choose workout to delete: ");

        for(int i = 0; i < tracker.getWorkoutCount(); i++){
            System.out.println(
                i + ". " + tracker.getWorkout(i).getWorkoutName()
            );
        }

        System.out.println("Enter workout number: ");
        int index = Integer.valueOf(scanner.nextLine());

        if(tracker.removeWorkout(index)){
            System.out.println("Workout deleted.");
        } else {
            System.out.println("Invalid workout number.");
        }
    }

    public void searchWorkout(){
        System.out.println("\nSearch Workout");
        System.out.println();

        System.out.println("1. Search by Exercise");
        System.out.println("2. Search by Date");
        int choice = Integer.valueOf(scanner.nextLine());

        if(choice == 1){
            System.out.println("Enter exercise name: ");
            String exerciseName = scanner.nextLine();

            ArrayList<Workout> results = tracker.searchByExercise(exerciseName);

            if(results.isEmpty()){
                System.out.println("No workouts found.");
            } else {
                System.out.println("Found workouts: ");

                for(Workout workout : results){
                    System.out.println("- " + workout.getWorkoutName());
                }
            }

        } else if (choice == 2){
            System.out.println("Enter date (YYYY-MM-DD): ");
            String input = scanner.nextLine();
            LocalDate date = LocalDate.parse(input);

            Workout workout = tracker.searchByDate(date);

            if(workout == null){
                System.out.println("No workouts found.");
            } else {
                System.out.println("Found workouts: ");
                System.out.println(workout.getWorkoutName());
            }
        }
    }

    public void showStatistics(){
        System.out.println("\nWorkout Statistics");
        System.out.println("------------------");

        System.out.println("Total workouts: " + tracker.getWorkoutCount());
        System.out.println("Total exercises: " + tracker.getTotalExercises());
        System.out.println("Total sets: " + tracker.getTotalSets());
        System.out.println("Total volume: " + tracker.getTotalVolume());
    }
}
