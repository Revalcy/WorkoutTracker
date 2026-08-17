# Workout Tracker

A Java console application for tracking workouts, exercises, and sets.

## Project Goal

This project is built to practice:

* Java
* Object-Oriented Programming
* Software design
* Git workflow

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
* View workout statistics, including average workout volume and most performed exercise
* Validate user input

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

The current application uses three main model classes:

* **Workout**

  * Stores workout name and date
  * Supports creating workouts with a supplied date
  * Supports editing the workout date
  * Manages a list of exercises
  * Protects its exercise collection from external modification
  * Calculates total workout volume

* **Exercise**

  * Stores exercise name
  * Manages a list of workout sets
  * Protects its set collection from external modification
  * Calculates total exercise volume

* **WorkoutSet**

  * Stores weight and repetitions for an individual set

## Project Structure

* **app**

  * Contains the main application entry point

* **model**

  * Contains workout-related data classes

* **service**

  * Contains workout management, search, and statistics logic

* **ui**

  * Contains the console user interface

## Version 2 Progress

Completed:

* Encapsulated workout, exercise, and set collections with unmodifiable views
* Added explicit workout-date support
* Updated date searches to return all workouts on a matching date
* Added average workout volume statistics
* Added most performed exercise statistics

Remaining:

* Edit existing workouts, exercises, and sets
* Save and load workout history using JSON

## Technologies

* Java
* Git
* GitHub

## Status

Version 2 in progress. Model and workout-tracker improvements are complete; persistence and workout editing are next.
