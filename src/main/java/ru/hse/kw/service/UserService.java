package ru.hse.kw.service;

import java.util.List;
import ru.hse.kw.model.User;



public interface UserService {
	
	User findById(int id);

	User findByLogin(String login);

	List<User> findAllUsers();
	
}
