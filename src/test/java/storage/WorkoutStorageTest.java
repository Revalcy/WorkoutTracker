package storage;

import com.workouttracker.model.Workout;
import com.workouttracker.storage.WorkoutStorage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutStorageTest {

    @Test
    void shouldSaveAndLoadWorkouts() throws IOException {
        Workout workout = new Workout("Push Day", LocalDate.of(2026, 8, 26));

        ObjectMapper mapper = new ObjectMapper();
        Path filePath = Path.of("test-workouts.json");

        WorkoutStorage storage = new WorkoutStorage(mapper, filePath);

        List<Workout> workouts = List.of(workout);

        storage.saveJson(workouts);

        List<Workout> result = storage.toList();

        assertEquals("Push Day", result.get(0).getWorkoutName());
    }
}
