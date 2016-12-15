package ru.hse.kw.dao;

/**
 * Created by vova on 15-Dec-16.
 */

import ru.hse.kw.model.User;

import java.util.List;

public interface UserDao {

    User findById(int id);

    void saveUser(User user);

    List<User> findAllUsers();
}