package ru.hse.kw.model;

import java.util.List;

/**
 * Created by Krist on 08.05.2016.
 */
public class User {
    private final long id;
    private final String login;
    private List<Task> tasks;
    private String password;

    public User(long id, String login, String password, List<Task> tasks) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.tasks = tasks;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
