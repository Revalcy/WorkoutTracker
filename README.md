# Workout Tracker

A Java console application for tracking workouts, exercises, and sets.

## Project Goal

This project is built to practice:

* Java
* Object-Oriented Programming
* Software design
* Git workflow
* Persistent data storage
* Application architecture
* Automated testing

The goal is to build a workout tracking application while improving programming and software development skills.

## Current Features

* Create and manage workouts
* Add exercises to workouts
* Record workout sets with weight and repetitions
* View saved workouts
* Delete workouts
* Search workouts by exercise name
* Search workouts by date
* Calculate exercise volume
* Calculate total workout volume
* View workout statistics
* Calculate total exercises
* Calculate total sets
* Calculate average workout volume
* Find the most performed exercise
* Validate user input
* Save workout data to a JSON file
* Load workout data when the application starts
* Automatically update saved workout data when workouts are added or deleted
* Automated testing with JUnit 5

## Persistent Storage

The application uses JSON-based persistent storage to save workout data between program runs.

The storage system uses:

* Jackson for JSON serialization and deserialization
* `WorkoutStorage` for handling file operations
* `WorkoutTracker` for managing workout data

Workout data follows this general flow:

```text
Application
    ↓
WorkoutTracker
    ↓
WorkoutStorage
    ↓
workouts.json
```

When the application starts, previously saved workouts are loaded from `workouts.json`.

When workouts are added or deleted, the updated workout list is automatically saved.

If saving fails while adding or deleting a workout, the application rolls back the change so the in-memory data remains consistent with the saved data.

## Workout Statistics

The application provides several statistics to help analyze workout activity:

* Total number of workouts
* Total number of exercises
* Total number of sets
* Total workout volume
* Average workout volume
* Most performed exercise

Workout volume is calculated using:

```text
Volume = Weight × Repetitions
```

The total workout volume is calculated by adding the volume of all exercises and sets within a workout.

## Input Validation

The application validates user input to prevent invalid data and program crashes.

* Validate integer input
* Validate positive integers
* Validate decimal number input
* Validate positive decimal numbers
* Prevent empty text input
* Validate date input
* Handle invalid menu and search choices

## Automated Testing

The project uses JUnit 5 for automated testing.

Current tests cover:

* `WorkoutSet`
* `Exercise`
* `Workout`
* `WorkoutTracker`
* `WorkoutStorage`

The tests currently verify:

* Storing workout set data
* Adding sets and exercises
* Calculating exercise volume
* Calculating workout volume
* Storing workout names and dates
* Searching workouts
* Calculating workout statistics
* Finding the most performed exercise
* Saving and loading workouts from JSON

The test suite currently contains 15 automated tests.

Tests can be run with:

```bash
mvn clean test
```

## Model Classes

The application uses three main model classes:

### Workout

* Stores workout name and date
* Manages a list of exercises
* Calculates total workout volume

### Exercise

* Stores exercise name
* Manages a list of workout sets
* Calculates total exercise volume

### WorkoutSet

* Stores weight and repetitions for an individual set

## Project Structure

```text
src/
├── main/
│   └── java/
│       └── com/
│           └── workouttracker/
│               ├── app/
│               │   └── Main.java
│               ├── model/
│               │   ├── Exercise.java
│               │   ├── Workout.java
│               │   └── WorkoutSet.java
│               ├── service/
│               │   └── WorkoutTracker.java
│               ├── storage/
│               │   └── WorkoutStorage.java
│               └── ui/
│                   └── Menu.java
│
└── test/
    └── java/
        ├── model/
        │   ├── WorkoutSetTest.java
        │   ├── ExerciseTest.java
        │   └── WorkoutTest.java
        ├── service/
        │   └── WorkoutTrackerTest.java
        └── storage/
            └── WorkoutStorageTest.java
```

### Package Responsibilities

* **`app`** — Contains the main application entry point
* **`model`** — Contains workout-related data classes
* **`service`** — Contains workout management and application logic
* **`storage`** — Handles persistent workout data
* **`ui`** — Contains the console user interface

## Technologies

* Java 11
* Jackson
* JUnit 5
* Maven
* Git
* GitHub
* JSON

## Project Status

**Version 1 — Completed**

Version 1 established the core workout tracking functionality, including workout management, exercises, sets, searching, volume calculations, and input validation.

**Version 2 — Completed**

Version 2 added persistent JSON storage and improved workout statistics.

### Version 2 Progress

* [x] Add JSON persistent storage
* [x] Serialize workouts to JSON
* [x] Deserialize workouts from JSON
* [x] Load workouts when the application starts
* [x] Save changes when workouts are added or deleted
* [x] Handle storage errors and maintain data consistency
* [x] Improve workout statistics
* [x] Calculate average workout volume
* [x] Find the most performed exercise

**Version 3 — In Progress**

Version 3 focuses on workout management improvements, advanced analytics, and automated testing.

### Version 3 Progress

* [x] Add JUnit 5
* [x] Create tests for `WorkoutSet`
* [x] Create tests for `Exercise`
* [x] Create tests for `Workout`
* [x] Create tests for `WorkoutTracker`
* [x] Create tests for `WorkoutStorage`
* [x] Test JSON save and load functionality
* [x] Verify workout statistics with automated tests
* [ ] Add workout editing functionality
* [ ] Add exercise and set editing functionality
* [ ] Add advanced workout analytics
* [ ] Add additional edge-case and error-handling tests
* [ ] Refactor and clean up the project
* [ ] Update documentation after Version 3 completion

## Planned Features

Future versions may include:

* Add additional workout management features
* Add database support
* Build a REST API with Spring Boot
* Create a frontend application
* Add advanced workout analytics
