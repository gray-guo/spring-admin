package com.weizhang.service;


import com.weizhang.dao.RoleDao;
import com.weizhang.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role getRow(int id){
        return roleDao.getRow(id);
    }

    public Role getName(String name){
        return roleDao.getName(name);
    }

    public List<Role> getList(){
        return roleDao.getList();
    }

    public int update(Role role){
        return roleDao.update(role);
    }

    public int inser(Role role){
        return roleDao.insert(role);
    }
}
