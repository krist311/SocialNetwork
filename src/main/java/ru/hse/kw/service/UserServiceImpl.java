package ru.hse.kw.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.hse.kw.dao.UserDao;
import ru.hse.kw.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl extends AbstractService<Integer, User> implements UserService{

	@Autowired
	private UserDao dao;

	public User findById(int id) {
		return dao.findById(id);
	}

	public void saveUser(User user) {
		dao.saveUser(user);
	}

	public User findByLogin(String login) {
		return dao.findByLogin(login);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public List<User> findUsersByIds(List<Integer> ids){
		List<User> users = new ArrayList<>();
		for (int id : ids){
			users.add(dao.findById(id));
		}
		return users;
	}

	public void save(User user) {
		dao.saveUser(user);
	}

	public void update(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setLogin(user.getLogin());
			entity.setInfo(user.getInfo());
			entity.setEmail(user.getEmail());
			entity.setPassword(user.getPassword());
		}
	}
}
