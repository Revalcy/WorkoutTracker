package model;

import com.workouttracker.model.Exercise;
import com.workouttracker.model.Workout;
import com.workouttracker.model.WorkoutSet;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    
    @Test
    void shouldAddExercise(){
        Workout workout = new Workout("Push Day");

        Exercise exercise = new Exercise("Bench Press");
        workout.addExercise(exercise);

        assertEquals(1, workout.getExercises().size());
    }

    @Test
    void shouldCalculateWorkoutVolume(){
        Workout workout = new Workout("Push Day");

        Exercise benchPress = new Exercise("Bench Press");
        benchPress.addSet(new WorkoutSet(135, 10));
        benchPress.addSet(new WorkoutSet(155, 8));

        Exercise shoulderPress = new Exercise("Shoulder Press");
        shoulderPress.addSet(new WorkoutSet(50, 10));

        workout.addExercise(benchPress);
        workout.addExercise(shoulderPress);

        assertEquals(3090, workout.getWorkoutVolume());

    }

    @Test 
    void shouldStoreWorkoutNameAndDate(){
        LocalDate date = LocalDate.of(2026, 8, 26);

        Workout workout = new Workout("Leg Day", date);

        assertEquals("Leg Day", workout.getWorkoutName());
        assertEquals(date, workout.getDate());
    }
}
