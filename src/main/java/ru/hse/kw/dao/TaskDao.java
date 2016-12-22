package ru.hse.kw.dao;

import ru.hse.kw.model.Task;

import java.util.List;

/**
 * Created by vova on 22-Dec-16.
 */
public interface TaskDao {
    public Task findById(int id);

    public List<Task> findByUserId(long user_id);

    public List<Task> findAllTasks();

    public void saveTask(Task task);

    public void updateTask(Task task);
}
