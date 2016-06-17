package ru.hse.kw.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.kw.model.Task;
import ru.hse.kw.model.User;

import java.util.List;

@Service("taskService")
@Transactional
public class TaskServiceImpl extends AbstractService<Integer, Task> implements TaskService{

	public Task findById(int id) {
		return getByKey(id);
	}

	public List<Task> findByUserId(int user_id) {

		Criteria criteria = createEntityCriteria();
		List <Task> results = criteria.add(Restrictions.eq("user_id", user_id)).list();
		return results;
	}

	public List<Task> findAllTasks() {
		Criteria criteria = createEntityCriteria();
		return (List<Task>) criteria.list();
	}
}
