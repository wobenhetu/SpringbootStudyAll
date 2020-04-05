package com.springboot.mybatis.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.mybatis.bean.MyUser;
import com.springboot.mybatis.bean.User;
import com.springboot.mybatis.mapper.MyUserMapper;
import com.springboot.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    @RequestMapping("/insertlist")
    public String insertUserList(){

        long begin = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());

        //以下代码会报   java.lang.OutOfMemoryError: Java heap space
     /*   List<MyUser> users = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            MyUser myUser = new MyUser();
            myUser.setId(Long.parseLong(i+""));
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
        userMapper.addUser(users);*/
//==========================================
        //插入1000万数据，耗时336秒，约5分钟
        for (int j = 0;j<10;j++) {
            List<MyUser> list = new ArrayList<>();
            for (int i = 0; i < 1000000; i++) {
                MyUser myUser = new MyUser();
                myUser.setId(Long.parseLong(i+j*1000000+""));
                myUser.setName("wulili"+i+j*1000000);
                if(i%2==0){
                    myUser.setAge("22");
                    myUser.setSex("men");
                }else {
                    myUser.setAge("23");
                    myUser.setSex("women");
                }
                list.add(myUser);
            }
            int index = list.size() / 10000;
            for (int i=0;i<index;i++){
                //stream流表达式，skip表示跳过前i*10000条记录，limit表示读取当前流的前10000条记录
                userMapper.addUser(list.stream().skip(i*10000).limit(10000).collect(Collectors.toList()));
            }
        }
////======================================

        long end = System.currentTimeMillis();
        System.out.println((end-begin)/1000);

        return "success";
    }
}
