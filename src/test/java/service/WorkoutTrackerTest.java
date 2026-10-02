package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workouttracker.model.Exercise;
import com.workouttracker.model.Workout;
import com.workouttracker.model.WorkoutSet;
import com.workouttracker.service.WorkoutTracker;
import com.workouttracker.storage.WorkoutStorage;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTrackerTest {
    @Test
    void shouldReturnWorkoutCount() {
        Workout workout1 = new Workout("Push Day");
        Workout workout2 = new Workout("Pull Day");

        List<Workout> workouts = new ArrayList<>();

        workouts.add(workout1);
        workouts.add(workout2);

        WorkoutTracker tracker = new WorkoutTracker(workouts, null);

        assertEquals(2, tracker.getWorkoutCount());
    }

    @Test
    void shouldSearchByExercise() {
        Workout pushDay = new Workout("Push Day");

        Exercise benchPress = new Exercise("Bench Press");
        pushDay.addExercise(benchPress);

        Workout pullDay = new Workout("Pull Day");

        Exercise pullUp = new Exercise("Pull Up");
        pullDay.addExercise(pullUp);

        List<Workout> workouts = new ArrayList<>();
        workouts.add(pushDay);
        workouts.add(pullDay);

        WorkoutTracker tracker = new WorkoutTracker(workouts, null);

        List<Workout> results = tracker.searchByExercise("Bench Press");

        assertEquals(1, results.size());
        assertEquals("Push Day", results.get(0).getWorkoutName());
    }

    @Test
    void shouldSearchByDate() {
        LocalDate date = LocalDate.of(2026, 8, 26);

        Workout workout1 = new Workout("Push Day", date);
        Workout workout2 = new Workout("Pull Day", LocalDate.of(2026, 8, 25));

        List<Workout> workouts = new ArrayList<>();
        workouts.add(workout1);
        workouts.add(workout2);

        WorkoutTracker tracker = new WorkoutTracker(workouts, null);

        List<Workout> results = tracker.searchByDate(date);

        assertEquals(1, results.size());
        assertEquals("Push Day", results.get(0).getWorkoutName());
    }

    @Test
    void shouldCalculateTotalExercises() {
        Workout pushDay = new Workout("Push Day");

        pushDay.addExercise(new Exercise("Bench Press"));
        pushDay.addExercise(new Exercise("Shoulder Press"));

        Workout pullDay = new Workout("Pull Day");

        pullDay.addExercise(new Exercise("Pull Up"));

        List<Workout> workouts = new ArrayList<>();
        workouts.add(pushDay);
        workouts.add(pullDay);

        WorkoutTracker tracker = new WorkoutTracker(workouts, null);

        assertEquals(3, tracker.getTotalExercises());
    }

    @Test
    void shouldCalculateTotalSets() {
        Workout workout = new Workout("Push Day");

        Exercise benchPress = new Exercise("Bench Press");
        benchPress.addSet(new WorkoutSet(135, 10));
        benchPress.addSet(new WorkoutSet(155, 8));

        Exercise shoulderPress = new Exercise("Shoulder Press");
        shoulderPress.addSet(new WorkoutSet(50, 10));

        workout.addExercise(benchPress);
        workout.addExercise(shoulderPress);

        List<Workout> workouts = new ArrayList<>();
        workouts.add(workout);

        WorkoutTracker tracker = new WorkoutTracker(workouts, null);

        assertEquals(3, tracker.getTotalSets());
    }

    @Test
    void shouldCalculateTotalVolume() {
        Workout workout = new Workout("Push Day");

        Exercise benchPress = new Exercise("Bench Press");
        benchPress.addSet(new WorkoutSet(135, 10));
        benchPress.addSet(new WorkoutSet(155, 8));

        workout.addExercise(benchPress);

        List<Workout> workouts = new ArrayList<>();

        workouts.add(workout);

        WorkoutTracker tracker = new WorkoutTracker(workouts, null);

        assertEquals(2590, tracker.getTotalVolume());
    }

    @Test
    void shouldCalculateAverageWorkoutVolume() {
        Workout workout = new Workout("Push Day");

        Exercise benchPress = new Exercise("Bench Press");
        benchPress.addSet(new WorkoutSet(100, 10));

        workout.addExercise(benchPress);

        Workout workout2 = new Workout("Pull Day");

        Exercise pullUp = new Exercise("Pull Up");
        pullUp.addSet(new WorkoutSet(50, 10));

        workout2.addExercise(pullUp);

        List<Workout> workouts = new ArrayList<>();

        workouts.add(workout);
        workouts.add(workout2);

        WorkoutTracker tracker = new WorkoutTracker(workouts, null);

        assertEquals(750, tracker.getAverageWorkoutVolume());
    }

    @Test
    void shouldFindMostPerformedExercise() {
        Workout workout = new Workout("Push Day");

        Exercise benchPress = new Exercise("Bench Press");
        benchPress.addSet(new WorkoutSet(135, 10));
        benchPress.addSet(new WorkoutSet(145, 8));

        workout.addExercise(benchPress);

        Workout workout2 = new Workout("Push Day 2");
        Exercise benchPress2 = new Exercise("Bench Press");
        benchPress2.addSet(new WorkoutSet(155, 8));

        workout2.addExercise(benchPress2);

        Exercise shoulderPress = new Exercise("Shoulder Press");
        shoulderPress.addSet(new WorkoutSet(50, 10));

        workout2.addExercise(shoulderPress);

        List<Workout> workouts = new ArrayList<>();
        workouts.add(workout);
        workouts.add(workout2);

        WorkoutTracker tracker = new WorkoutTracker(workouts, null);

        assertEquals("Bench Press", tracker.getMostPerformedExercise());

    }

    @Test
    void shouldEditWorkout() {
        Path filePath = Path.of("test-workouts.json");
        ObjectMapper mapper = new ObjectMapper();

        WorkoutStorage storage = new WorkoutStorage(mapper, filePath);

        Workout workout = new Workout("Push Day");

        Exercise benchPress = new Exercise("Bench Press");
        benchPress.addSet(new WorkoutSet(135, 10));
        benchPress.addSet(new WorkoutSet(145, 8));
        workout.addExercise(benchPress);

        List<Workout> workouts = new ArrayList<>();
        workouts.add(workout);

        WorkoutTracker tracker = new WorkoutTracker(workouts, storage);
        boolean result = tracker.editWorkout(0, "Chest Day", LocalDate.of(2026, 9, 29));

        assertEquals("Chest Day", workout.getWorkoutName());
        assertEquals(LocalDate.of(2026, 9, 29), workout.getDate());
        assertEquals(true, result);
    }

    @Test
    void shouldEditExercise() {
        Path filePath = Path.of("test-workouts.json");
        ObjectMapper mapper = new ObjectMapper();

        WorkoutStorage storage = new WorkoutStorage(mapper, filePath);

        Workout workout = new Workout("Push Day");
        Exercise exercise = new Exercise("Bench Press");
        exercise.addSet(new WorkoutSet(135, 10));
        workout.addExercise(exercise);

        List<Workout> workouts = new ArrayList<>();
        workouts.add(workout);

        WorkoutTracker tracker = new WorkoutTracker(workouts, storage);
        boolean result = tracker.editExercise(0, 0, "Shoulder Press");

        assertEquals(true, result);
        assertEquals("Shoulder Press", exercise.getName());
    }

    @Test
    void shouldEditWorkoutSet(){
        Path filePath = Path.of("test-workouts.json");
        ObjectMapper mapper = new ObjectMapper();

        WorkoutStorage storage = new WorkoutStorage(mapper, filePath);

        Workout workout = new Workout("Push Day");
        Exercise benchPress = new Exercise("Bench Press");
        benchPress.addSet(new WorkoutSet(135, 10));
        workout.addExercise(benchPress);

        List<Workout> workouts = new ArrayList<>();
        workouts.add(workout);

        WorkoutTracker tracker = new WorkoutTracker(workouts, storage);

        boolean result = tracker.editWorkoutSet(0, 0, 0, 185, 8);

        assertEquals(true, result);
        assertEquals(185, benchPress.getSets().get(0).getWeight());
        assertEquals(8, benchPress.getSets().get(0).getReps());

    }
}