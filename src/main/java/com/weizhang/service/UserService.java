package com.weizhang.service;


import com.weizhang.dao.UserDao;
import com.weizhang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserDao userDao;

    public User getRow(int id){
        return userDao.getRow(id);
    }


    public User getUserByName(String name){
     return userDao.getUserByName(name);
    }

    public List<User> getList(){
        return userDao.getList();
    }

    public int update(User user){
        return userDao.update(user);
    }

    public int inser(User user){
        return userDao.insert(user);
    }


    public List<User> getUserRoleList(int uid){
        return userDao.getUserRoleList(uid);
    }



    public int insertMiddleData(int uid, int roleid){
        System.out.println("user_id : " + uid);
        System.out.println("role_id : " + roleid);
        return userDao.insertMiddleData(uid, roleid);
    }

    public int deleteMiddleData(int uid){
        return userDao.deleteMiddleData(uid);
    }

}
