package model;

public class WorkoutSet{
    private double weight;
    private int reps;

    public WorkoutSet(double weight, int reps){
        this.weight = weight;
        this.reps = reps;
    }

    public double getWeight(){
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public int getReps(){
        return reps;
    }

    public void setReps(int reps){
        this.reps = reps;
    }

    @Override
    public String toString(){
        return weight + " lbs x " + reps + " reps";
    }
}