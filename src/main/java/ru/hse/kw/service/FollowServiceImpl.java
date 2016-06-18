package ru.hse.kw.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.kw.model.Follow;
import ru.hse.kw.model.Task;

import java.util.List;

@Service("followService")
@Transactional
public class FollowServiceImpl extends AbstractService<Integer, Follow> implements FollowService{

	public List<Long> getFollowingList(long user_id) {
		Criteria criteria = createEntityCriteria();
		criteria.setProjection(Projections.property("user_id_who"));
		List <Long> results = criteria.add(Restrictions.eq("user_id_who", user_id)).list();
		return results;
	}

	public List<Long> getFollowersList(long user_id){
		Criteria criteria = createEntityCriteria();
		criteria.setProjection(Projections.property("user_id_on_whom"));
		List <Long> results = criteria.add(Restrictions.eq("user_id_on_whom", user_id)).list();
		return results;
	}

	public void save(Follow follow) {
		persist(follow);
	}

}
