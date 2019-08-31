package com.weizhang.dao;

import com.weizhang.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "createdAt", column = "created_at"),
    })


    @Select("SELECT * FROM sys_role")
    List<Role> getList();

    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    Role getRow(int id);

    @Select("SELECT * FROM sys_role WHERE name = #{name}")
    Role getName(String name);


    @Update("UPDATE sys_role SET name = #{name}")
    int update(Role role);


    @Insert("INSERT INTO sys_role (`name`, `crated_at`) VALUES (#{name}, #{cratedAt})")
    @Options(useGeneratedKeys = true) //返回主键id
    int insert(Role role);
}
