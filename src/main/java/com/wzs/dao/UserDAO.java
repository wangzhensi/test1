package com.wzs.dao;

import com.wzs.domain.User;
import java.util.List;
import java.util.Map;

public interface UserDAO {

    List<User> findAll();

    void save(User user);

    User findById(Integer id);

    List<Map<String, Object>> findAllMap();

}
