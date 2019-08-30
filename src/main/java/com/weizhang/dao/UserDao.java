package com.weizhang.dao;


import com.weizhang.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "status", column = "status"),
            @Result(property = "cratedAt", column = "crated_at"),
    })


    @Select("SELECT * FROM sys_user")
    List<User> getList();

    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    User getRow(int id);


    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    User getUserByName(String name);
}
