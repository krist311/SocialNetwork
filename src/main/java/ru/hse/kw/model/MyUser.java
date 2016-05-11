package ru.hse.kw.model;

import java.util.List;

/**
 * Created by Krist on 08.05.2016.
 */
public class MyUser {
    private final long id;
    private final String name;
    private final String lastName;
    private List<Task> tasks;

    public MyUser(long id, String name, String lastName, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
