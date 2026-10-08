package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.workouttracker.model.WorkoutSet;

public class WorkoutSetTest { 
    
    @Test
    void shouldStoreWeightAndReps() {
        WorkoutSet set = new WorkoutSet(135, 10);

        assertEquals(135, set.getWeight());
        assertEquals(10, set.getReps());
    }
}

