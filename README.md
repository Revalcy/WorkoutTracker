# Workout Tracker

A Java console application for tracking workouts, exercises, and sets.

## Project Goal

This project is built to practice:

* Java
* Object-Oriented Programming
* Software design
* Git workflow
* Persistent data storage

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

## Input Validation

The application validates user input to prevent invalid data and program crashes.

* Validate integer input
* Validate positive integers
* Validate decimal number input
* Validate positive decimal numbers
* Prevent empty text input
* Validate date input
* Handle invalid menu and search choices

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
src/main/java/
├── app/
│   └── Main.java
├── model/
│   ├── Exercise.java
│   ├── Workout.java
│   └── WorkoutSet.java
├── service/
│   └── WorkoutTracker.java
├── storage/
│   └── WorkoutStorage.java
└── ui/
    └── Menu.java
```

### Package Responsibilities

* **app** — Contains the main application entry point
* **model** — Contains workout-related data classes
* **service** — Contains workout management and application logic
* **storage** — Handles persistent workout data
* **ui** — Contains the console user interface

## Planned Features

* Add additional workout management features
* Add database support
* Build a REST API with Spring Boot
* Create a frontend application
* Add advanced workout analytics

## Technologies

* Java
* Jackson
* Maven
* Git
* GitHub
* JSON

## Status

**Version 1 — Completed**

**Version 2 — In Progress**

### Version 2 Progress

* [x] Add JSON persistent storage
* [x] Serialize workouts to JSON
* [x] Deserialize workouts from JSON
* [x] Load workouts when the application starts
* [x] Save changes when workouts are added or deleted
* [x] Improve workout statistics
* [ ] Add additional Version 2 features
