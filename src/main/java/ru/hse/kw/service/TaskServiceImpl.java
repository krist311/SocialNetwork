package ru.hse.kw.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.kw.dao.TaskDao;
import ru.hse.kw.model.Task;

import java.util.List;

@Service("taskService")
@Transactional
public class TaskServiceImpl extends AbstractService<Integer, Task> implements TaskService{

	@Autowired
	TaskDao dao;

	public Task findById(int id) {
		return dao.findById(id);
	}

	public List<Task> findByUserId(long user_id) {
		return dao.findByUserId(user_id);
	}

	public List<Task> findAllTasks() {
		return dao.findAllTasks();
	}

	public void saveTask(Task task) {
		dao.saveTask(task);
	}

	public void updateTask(Task task) {
		Task entity = dao.findById(task.getId());
		if(entity!=null){
			entity.setName(task.getName());
			entity.setDescription(task.getDescription());
			entity.setProgress(task.getProgress());
			entity.setDate(task.getDate());
		}
	}
}
