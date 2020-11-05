package com.wzs.test;

import com.wzs.dao.UserDAO;
import com.wzs.domain.User;
import com.wzs.service.UserService;
import com.wzs.util.ServiceFactory;
import com.wzs.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MybatisTest {
    private final UserService service = ServiceFactory.getService();

    @Test
    public void testFindAll() {
        List<User> userList = service.findAll();
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                System.out.println(user);
            }
        }
    }

    @Test
    public void testFindAllMap() {
        UserDAO userDAO = SqlSessionUtil.getSession().getMapper(UserDAO.class);
        List<Map<String, Object>> mapList = userDAO.findAllMap();
        for (Map<String, Object> map : mapList) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                System.out.println("key : " + key);
                System.out.println("value : " + map.get(key));
            }
            System.out.println("---------------------");
        }
    }

    @Test
    public void testSave() {
        UserService service = ServiceFactory.getService();
        User user = new User(65, "小垃圾", new Date(new java.util.Date().getTime()), "男", "天津市");
        service.save(user);
    }

    @Test
    public void testFindById() {
        User user = service.findById(64);
        System.out.println(user);
    }

    @Test
    public void testSave1() {
        User user = new User(65, "小垃圾", new Date(new java.util.Date().getTime()), "男", "天津市");
        UserDAO userDAO = SqlSessionUtil.getSession().getMapper(UserDAO.class);
        userDAO.save(user);
    }

    @Test
    public void testMybatis() {
        InputStream is = MybatisTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();
        UserDAO userDAO = session.getMapper(UserDAO.class);
        User user = new User(65, "小垃圾", new Date(new java.util.Date().getTime()), "男", "天津市");
        userDAO.save(user);
        session.commit();

    }
}
