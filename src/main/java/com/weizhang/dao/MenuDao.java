package com.weizhang.dao;


import com.weizhang.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuDao {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "url", column = "url"),
            @Result(property = "type", column = "type"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "type", column = "type"),
            @Result(property = "name", column = "name"),
            @Result(property = "perms", column = "perms"),
            @Result(property = "sort", column = "sort"),


    })


    @Select("SELECT * FROM sys_menu")
    List<Menu> getList();

    @Select("SELECT * FROM sys_menu WHERE id = #{id}")
    Menu getRow(int id);


    @Select("SELECT * FROM sys_menu WHERE name = #{name}")
    Menu getUserByName(String name);
}
