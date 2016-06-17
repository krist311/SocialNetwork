package ru.hse.kw.service;

import java.util.List;

import ru.hse.kw.model.Task;
import ru.hse.kw.model.User;



public interface TaskService {

    Task findById(int id);

    List<Task> findByUserId(int user_id);

    List<Task> findAllTasks();

}
