package com.weizhang.dao;


import com.weizhang.model.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PermissionDao {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "perm", column = "perm"),
            @Result(property = "type", column = "type"),
            @Result(property = "createdAt", column = "created_at"),
    })


    @Select("SELECT * FROM sys_permission")
    List<Permission> getList();

    @Select("SELECT * FROM sys_permission WHERE id = #{id}")
    Permission getRow(int id);

    @Select("SELECT * FROM sys_permission WHERE name = #{name}")
    Permission getName(String name);


    @Update("UPDATE sys_permission SET name = #{name}, perm = #{perm}, type = #{type}")
    int update(Permission permission);


    @Insert("INSERT INTO sys_permission (`name`, `perm`, `type`, `crated_at`) VALUES (#{name}, #{perm}, #{type}, #{cratedAt})")
    @Options(useGeneratedKeys = true) //返回主键id
    int insert(Permission permission);
}
