package model;

import java.util.ArrayList;

public class Exercise {
    private String name;
    private ArrayList<WorkoutSet> sets;

    public Exercise(String name){
        this.name = name;
        sets = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public ArrayList<WorkoutSet> getSets(){
        return sets;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addSet(WorkoutSet set){
        sets.add(set);
    }

    public void removeSet(int index){
        sets.remove(index);
    }

    public double getTotalVolume(){
        double totalVolume = 0;

        for(WorkoutSet set : sets){
            totalVolume += set.getWeight() * set.getReps();
        }

        return totalVolume;
    }

    @Override
    public String toString(){
        return name;
    }
}