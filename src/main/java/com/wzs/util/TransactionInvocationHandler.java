package com.wzs.util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler<T> implements InvocationHandler {

    private final T target;

    public TransactionInvocationHandler(T target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        SqlSession session = SqlSessionUtil.getSession();
        try {
            obj = method.invoke(target, args);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            SqlSessionUtil.release(session);
        }
        return obj;
    }

    public T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }
}
