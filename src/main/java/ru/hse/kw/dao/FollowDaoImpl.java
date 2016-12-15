package ru.hse.kw.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.hse.kw.model.Follow;
import ru.hse.kw.model.User;

import java.util.List;

/**
 * Created by vova on 15-Dec-16.
 */
@Repository("followDao")
public class FollowDaoImpl extends AbstractDao<Integer, Follow> implements FollowDao{
    public List<Integer> getFollowingList(int user_id) {
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.property("user_id_on_whom"));
        List <Integer> results = criteria.add(Restrictions.eq("user_id_who", user_id)).list();
        return results;
    }

    public List<Integer> getFollowersList(int user_id){
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.property("user_id_who"));
        List <Integer> results = criteria.add(Restrictions.eq("user_id_on_whom", user_id)).list();
        return results;
    }

    public void save(Follow follow) {
        persist(follow);
    }
}
