package ru.hse.kw.service;

import java.util.List;

import ru.hse.kw.model.Task;
import ru.hse.kw.model.User;



public interface TaskService {

    Task findById(int id);

    List<Task> findByUserId(long user_id);

    List<Task> findAllTasks();

    void save(Task task);

    void update(Task task);
}

