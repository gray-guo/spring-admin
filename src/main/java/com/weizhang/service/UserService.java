package com.weizhang.service;


import com.github.pagehelper.PageHelper;
import com.weizhang.dao.UserDao;
import com.weizhang.model.Menu;
import com.weizhang.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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




}
