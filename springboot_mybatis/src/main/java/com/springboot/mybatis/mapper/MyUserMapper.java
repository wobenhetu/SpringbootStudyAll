package com.springboot.mybatis.mapper;

import com.springboot.mybatis.bean.MyUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface MyUserMapper {
    List<MyUser> getAll();

    int deleteByPrimaryKey(Long id);

    int insert(MyUser record);

    int insertSelective(MyUser record);

    MyUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MyUser record);

    int updateByPrimaryKey(MyUser record);
}