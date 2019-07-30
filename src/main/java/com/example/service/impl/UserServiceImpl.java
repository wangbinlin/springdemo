package com.example.service.impl;

import com.example.annotation.ExtTransaction;
import com.example.dao.UserDao;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        userDao.save("test4", "test4");
        System.out.println("执行save2方法");
        int i=1/0;
        userDao.save("test5", "test5");
    }


    @Transactional
    @Override
    public void save2() {
        userDao.save("test4", "test4");
        System.out.println("执行save2方法");
        int i=1/0;
        userDao.save("test5", "test5");
    }
}
