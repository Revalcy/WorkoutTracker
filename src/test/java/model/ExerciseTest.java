package model;

import com.workouttracker.model.Exercise;
import com.workouttracker.model.WorkoutSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    
    @Test
    void shouldAddSet(){
        Exercise exercise = new Exercise("Bench Press");

        WorkoutSet set = new WorkoutSet(135, 10);
        exercise.addSet(set);

        assertEquals(1, exercise.getSets().size());
    }

    @Test
    void shouldCalculateTotalVolume(){
        Exercise exercise = new Exercise("Bench Press");

        WorkoutSet set1 = new WorkoutSet(135, 10);
        WorkoutSet set2 = new WorkoutSet(155, 8);

        exercise.addSet(set1);
        exercise.addSet(set2);

        assertEquals(2590, exercise.getTotalVolume());
    }
}
