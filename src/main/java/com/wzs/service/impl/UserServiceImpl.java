package com.wzs.service.impl;

import com.wzs.dao.UserDAO;
import com.wzs.domain.User;
import com.wzs.service.UserService;
import com.wzs.util.SqlSessionUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = SqlSessionUtil.getSession().getMapper(UserDAO.class);

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public User findById(Integer id) {
        return userDAO.findById(id);
    }
}
