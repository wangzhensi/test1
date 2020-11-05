package com.wzs.util;

import com.wzs.service.UserService;
import com.wzs.service.impl.UserServiceImpl;

public class ServiceFactory {

    public static UserService getService() {
        UserService userService = new UserServiceImpl();
        return new TransactionInvocationHandler<UserService>(userService).getProxy();
    }
}
