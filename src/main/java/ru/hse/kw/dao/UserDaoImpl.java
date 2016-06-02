package ru.hse.kw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.kw.model.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findById(int id) {
        return getByKey(id);
    }

    public User findByLogin(String login) {

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        return (User) criteria.uniqueResult();
    }

    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }
}