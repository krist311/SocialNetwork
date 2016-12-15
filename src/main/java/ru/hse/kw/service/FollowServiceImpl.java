package ru.hse.kw.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.kw.dao.FollowDao;
import ru.hse.kw.model.Follow;
import ru.hse.kw.model.Task;

import java.util.List;

@Service("followService")
@Transactional
public class FollowServiceImpl extends AbstractService<Integer, Follow> implements FollowService{

	@Autowired
	private FollowDao dao;

	public List<Integer> getFollowingList(int user_id) {
		return dao.getFollowingList(user_id);
	}

	public List<Integer> getFollowersList(int user_id){
		return dao.getFollowersList(user_id);
	}

	public void save(Follow follow) {
		dao.save(follow);
	}

}
