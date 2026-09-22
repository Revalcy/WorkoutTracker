package model;

import com.workouttracker.model.WorkoutSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutSetTest { 
    
    @Test
    void shouldStoreWeightAndReps(){
        WorkoutSet set = new WorkoutSet(135, 10);

        assertEquals(135, set.getWeight());
        assertEquals(10, set.getReps());
    }
}

