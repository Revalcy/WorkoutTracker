package ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

            int choice = getIntegerInput();

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
        String workoutName = getNonEmptyInput();

        Workout workout = new Workout(workoutName);

        System.out.println("How many exercises? ");
        int exerciseCount = getPositiveIntegerInput();

        for(int i = 0; i < exerciseCount; i++){
            System.out.println("Enter exercise name: ");
            String exerciseName = getNonEmptyInput();

            Exercise exercise = new Exercise(exerciseName);

            System.out.println("How many sets? ");
            int setCount = getPositiveIntegerInput();

            for(int j = 0; j < setCount; j++){
                System.out.println("Set " + (j + 1));

                System.out.println("Weight: ");
                double weight = getPositiveDoubleInput();

                System.out.println("Reps: ");
                int reps = getPositiveIntegerInput();

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
        int index = getIntegerInput();

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
        int choice = getIntegerInput();

        if(choice == 1){
            System.out.println("Enter exercise name: ");
            String exerciseName = getNonEmptyInput();

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
            LocalDate date = getDateInput();

            Workout workout = tracker.searchByDate(date);

            if(workout == null){
                System.out.println("No workouts found.");
            } else {
                System.out.println("Found workouts: ");
                System.out.println(workout.getWorkoutName());
            }
        } else {
            System.out.println("Invalid choice.");
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

    private int getIntegerInput(){
        while(true){
            try{
                return Integer.valueOf(scanner.nextLine());
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private int getPositiveIntegerInput(){
        while(true){
            int number = getIntegerInput();

            if(number <= 0){
                System.out.println("Please enter a positive number.");
            } else {
                return number;
            }
        }
    }

    private double getDoubleInput(){
        while(true){
            try{
                return Double.valueOf(scanner.nextLine());
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double getPositiveDoubleInput(){
        while(true){
            double number = getDoubleInput();

            if(number <= 0){
                System.out.println("Please enter a positive number.");
            } else {
                return number;
            }
        }
    }

    private String getNonEmptyInput(){
        while(true){

            String input = scanner.nextLine();

            if(input.isEmpty()){
                System.out.println("Input cannot be empty.");
            } else {
                return input;
            }
        }
    }

    private LocalDate getDateInput(){
        while(true){
            try{
                String input = scanner.nextLine();
                LocalDate date = LocalDate.parse(input);
                return date;
            } catch(DateTimeParseException e){
                System.out.println("Please enter a valid date.");
            }
        }
    }
}
