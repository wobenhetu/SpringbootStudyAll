package com.springboot.mybatis.controller;

import com.springboot.mybatis.bean.MyUser;
import com.springboot.mybatis.bean.User;
import com.springboot.mybatis.mapper.MyUserMapper;
import com.springboot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JdbcController {


    @Autowired
    private UserMapper myuserMapper;


    @Autowired
    private MyUserMapper userMapper;

    @RequestMapping("/getlist")
    public List<User> getUserList() {
        List<User> list = myuserMapper.getAll();
        return list;
    }

    @RequestMapping("/getlist1")
    public List<MyUser> getUserList1() {
        List<MyUser> list = userMapper.getAll();
        return list;
    }

}
