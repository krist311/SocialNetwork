package ru.hse.kw.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import ru.hse.kw.model.MyUser;
import ru.hse.kw.model.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.hse.kw.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	private static List<MyUser> myUsers;
	
	static{
		users= populateDummyUsers();
		myUsers=getFakeUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	public List<MyUser> findAllMyUsers() {
		return myUsers;
	}
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
    public MyUser findMyUserById(long id){
        for(MyUser user : myUsers){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"Sam", "NY", "sam@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Tomy", "ALBAMA", "tomy@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Kelly", "NEBRASKA", "kelly@abc.com"));
		return users;
	}
	private static List<MyUser> getFakeUsers(){
		List<MyUser> users = new ArrayList<>();
		List<Task> tasks = new LinkedList<>();
		tasks.add(new Task("Drink VODKA",100));
		users.add(new MyUser(counter.incrementAndGet(),"Bomzh","Vasiliy",tasks));
		return users;
	}

}
