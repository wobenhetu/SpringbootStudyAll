package com.springboot.mybatis.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.mybatis.bean.MyUser;
import com.springboot.mybatis.bean.User;
import com.springboot.mybatis.mapper.MyUserMapper;
import com.springboot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @PostMapping("/insertuser")
    public int insertUser(@RequestBody MyUser myUser){
        return userMapper.insert(myUser);
    }

    //http://localhost:8096/getUserList?pageNum=1&pageSize=2
    @RequestMapping("/getUserList")
    public Page<MyUser> getUserList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Page<MyUser>  userList= userMapper.getAll();
        return userList;
    }

    @RequestMapping("/insertusers")
    public String insertUsers(){

        List<MyUser> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            MyUser myUser = new MyUser();
            myUser.setId(Long.parseLong(i+8+""));
            myUser.setName("wulili"+i);
            if(i%2==0){
                myUser.setAge("22");
                myUser.setSex("men");
            }else {
                myUser.setAge("23");
                myUser.setSex("women");
            }
            users.add(myUser);
        }
        for (int i = 0; i < users.size(); i++) {
            userMapper.insert(users.get(i));
        }

        return "success";
    }

}
