package ru.hse.kw.service;

import ru.hse.kw.model.Follow;
import ru.hse.kw.model.Task;

import java.util.List;


public interface FollowService {

    List<Integer> getFollowingList(int user_id);

    List<Integer> getFollowersList(int user_id);

    void save(Follow follow);
}

