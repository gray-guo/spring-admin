package com.weizhang.dao;


import com.weizhang.model.Menu;
import org.apache.ibatis.annotations.*;

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
            @Result(property = "sort", column = "sort"),

    })


    @Select("SELECT * FROM sys_menu")
    List<Menu> getList();

    @Select("SELECT * FROM sys_menu WHERE id = #{id}")
    Menu getRow(int id);


    @Select("SELECT * FROM sys_menu WHERE name = #{name}")
    Menu getUserByName(String name);


    @Insert("INSERT INTO sys_menu (`url`, `type`, `parent_id`, `name`, `sort`) VALUES (#{url}, #{type}, #{parentId}, #{name}, #{sort})")
    @Options(useGeneratedKeys = true) //返回主键id
    int insert(Menu menu);
}
