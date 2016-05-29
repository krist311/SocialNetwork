package ru.hse.kw.service;

import ru.hse.kw.model.User;

import java.util.List;


public interface UserService {
	
	User findById(long id);

	List<User> findAllUsers();
	
}
