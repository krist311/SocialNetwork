package ru.hse.kw.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.hse.kw.model.Task;

import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends AbstractDao<Integer, Task> implements TaskDao {

    public Task findById(int id) {
        return getByKey(id);
    }

    public List<Task> findByUserId(long user_id) {
        Criteria criteria = createEntityCriteria();
        List <Task> results = criteria.add(Restrictions.eq("user_id", user_id)).list();
        return results;
    }

    public List<Task> findAllTasks() {
        Criteria criteria = createEntityCriteria();
        return (List<Task>) criteria.list();
    }

    public void saveTask(Task task) {
        persist(task);
    }

    public void updateTask(Task task) {
        update(task);
    }
}
