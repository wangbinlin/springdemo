package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * UserDao
 *
 * @author wangbinlin
 * @version V1.0
 * @date 2019/7/25
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String name, String password) {
        String sql = "INSERT INTO tb_user(username, password,created,updated) VALUES(?,?,?,?);";
        int result = jdbcTemplate.update(sql, name, password,new Date(),new Date());
        System.out.println("执行结果:result=" + result);
    }
}
