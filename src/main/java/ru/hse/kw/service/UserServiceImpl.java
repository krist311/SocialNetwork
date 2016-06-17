package ru.hse.kw.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.hse.kw.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl extends AbstractService<Integer, User> implements UserService{

	public User findById(int id) {
		return getByKey(id);
	}

	public User findByLogin(String login) {

		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("login", login));
		User user = (User) criteria.uniqueResult();
		return user;
	}

	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}
}
