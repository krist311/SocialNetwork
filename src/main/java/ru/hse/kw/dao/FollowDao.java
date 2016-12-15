package ru.hse.kw.dao;

import ru.hse.kw.model.Follow;

import java.util.List;

/**
 * Created by vova on 15-Dec-16.
 */
public interface FollowDao {
    List<Integer> getFollowingList(int user_id);

    List<Integer> getFollowersList(int user_id);

    void save(Follow follow);
}
