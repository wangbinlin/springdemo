package com.example.service.impl;

import com.example.annotation.ExtTransaction;
import com.example.dao.UserDao;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author wangbinlin
 * @version V1.0
 * @date 2019/7/25
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void add() {
        userDao.add("test3", "test3");
    }

    @ExtTransaction
    @Override
    public void save() {
        int i=1/0;
        userDao.save("test1", "test1");
        System.out.println("执行save2方法");
        userDao.save("test2", "test2");
    }
}
