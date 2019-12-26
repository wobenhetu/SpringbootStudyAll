package com.springboot.mybatis.controller;

import com.springboot.mybatis.bean.User;
import com.springboot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JdbcController {


    @Autowired
    private UserMapper myuserMapper;


    @RequestMapping("/getlist")
    public List<User> getUserList() {
        List<User> list = myuserMapper.getAll();
        return list;
    }


   /* @Resource
    private JdbcTemplate jdbcTemplate;*/

   /* @RequestMapping("/getlist")
    public String getUserList() {

        String sql = "SELECT * FROM user";

        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {

            User user = null;

            @Override
            public User mapRow (ResultSet rs, int rowNum) throws SQLException

            {

                user = new User();

                user.setId(rs.getString("id"));

                user.setName(rs.getString("name"));

                user.setSex(rs.getString("sex"));

                user.setAge(rs.getString("age"));

                return user;

            }
        });

        String result = JSON.toJSONString(userList);

       *//* for (int i=0;i<userList.size();i++){
            result += userList.get(i).getId()+" ";
            result += userList.get(i).getName()+" ";
            result += userList.get(i).getAge()+" ";
            result += userList.get(i).getSex()+" ";
        }*//*
        return result;

    }*/



}
