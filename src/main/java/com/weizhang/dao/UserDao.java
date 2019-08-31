package com.weizhang.dao;


import com.weizhang.model.User;
import org.apache.ibatis.annotations.*;

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


    @Select("SELECT * FROM sys_user WHERE `name` = #{name}")
    User getUserByName(String name);

    @Update("UPDATE sys_user SET password = #{password}")
    int update(User user);


    @Insert("INSERT INTO sys_user (`name`, `password`, `crated_at`) VALUES (#{name}, #{password},#{cratedAt})")
    @Options(useGeneratedKeys = true) //返回主键id
    int insert(User user);


    @Select("SELECT r.`name` as role_name, r.id as role_id FROM sys_user AS u INNER JOIN sys_user_role AS m ON u.id=m.sys_user_id INNER JOIN sys_role AS r ON m.sys_role_id=r.id WHERE u.id = #{uid}")
    @Results({
            @Result( property = "id", column = "id"),
            @Result( property = "name", column = "name"),
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name")
    })
    List<User> getUserRoleList(int uid);

    @Insert("INSERT INTO sys_user_role (`sys_user_id`, `sys_role_id`) VALUES (#{param1}, #{param2})")
    int insertMiddleData(int uid, int roleid);


    /**
     * 删除中间表数据
     * @param uid
     * @return
     */
    @Delete("DELETE FROM `sys_user_role` WHERE `sys_user_id` = #{param1}")
    int deleteMiddleData(int uid);
}
