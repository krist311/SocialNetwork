package ru.hse.kw.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import ru.hse.kw.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.hse.kw.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= getFakeUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	private static List<User> getFakeUsers(){
		List<User> users = new ArrayList<>();
		List<Task> tasks = new LinkedList<>();
		tasks.add(new Task("Drink VODKA",100));
		users.add(new User(counter.incrementAndGet(),"Bomzh","Vasiliy",tasks));
		return users;
	}

}
