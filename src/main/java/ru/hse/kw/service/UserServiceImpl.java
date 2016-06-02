package ru.hse.kw.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import ru.hse.kw.dao.UserDao;
import ru.hse.kw.dao.UserDaoImpl;
import ru.hse.kw.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.hse.kw.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByLogin(String login) {
		User user = dao.findByLogin(login);
		return user;
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

}
