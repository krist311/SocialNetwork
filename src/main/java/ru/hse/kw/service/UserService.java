package ru.hse.kw.service;

import java.util.List;

import ru.hse.kw.model.MyUser;
import ru.hse.kw.model.User;



public interface UserService {
	
	User findById(long id);

	MyUser findMyUserById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers();

	List<MyUser> findAllMyUsers();
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
}
