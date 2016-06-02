package ru.hse.kw.dao;


import java.util.List;

import ru.hse.kw.model.User;

public interface UserDao {

    User findById(int id);

    User findByLogin(String login);

    List <User> findAllUsers();

}