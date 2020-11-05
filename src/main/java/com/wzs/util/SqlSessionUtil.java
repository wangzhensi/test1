package com.wzs.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

public class SqlSessionUtil {

    private static SqlSessionFactory factory;
    private static final ThreadLocal<SqlSession> t = new ThreadLocal<SqlSession>();

    static {
        InputStream is = SqlSessionUtil.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    public static SqlSession getSession() {
        SqlSession session = t.get();
        if (session == null) {
            session = factory.openSession();
            t.set(session);
        }
        return session;
    }


    public static void release(SqlSession session) {
        if (session != null) {
            session.close();
            t.remove();
        }
    }
}
