package com.wzs.service;

import com.wzs.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> findAll();

    void save(User user);

    User findById(Integer id);

}
