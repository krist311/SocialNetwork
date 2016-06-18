package ru.hse.kw.service;

import ru.hse.kw.model.Follow;
import ru.hse.kw.model.Task;

import java.util.List;


public interface FollowService {

    List<Long> getFollowingList(long user_id);

    List<Long> getFollowersList(long user_id);

    void save(Follow follow);
}

