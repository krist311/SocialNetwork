package ru.hse.kw.model;

/**
 * Created by Krist on 08.05.2016.
 */
public class Task {
    private final String name;
    private int progress;
    private final int goal;
    public Task(String name, int goal){
        this.name = name;
        progress=0;
        this.goal = goal;
    }
    public int getProgress(){
        return progress;
    }
    public String getName(){
        return name;
    }
    public void increaseProgress(){
        progress++;
    }
    public int getGoal(){
        return goal;
    }
}
