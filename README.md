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
* View workout statistics

## Model Classes

The current application uses three main model classes:

* **Workout**

  * Stores workout name and date
  * Manages a list of exercises
  * Calculates total workout volume

* **Exercise**

  * Stores exercise name
  * Manages a list of workout sets
  * Calculates total exercise volume

* **WorkoutSet**

  * Stores weight and repetitions for an individual set

## Project Structure

* **app**

  * Contains the main application entry point

* **model**

  * Contains workout-related data classes

* **service**

  * Contains workout management logic

* **ui**

  * Contains the console user interface

## Planned Features

* Save workout history
* Add database support
* Build REST API with Spring Boot
* Create a frontend application
* Add advanced workout analytics

## Technologies

* Java
* Git
* GitHub

## Status

Version 1 Completed
