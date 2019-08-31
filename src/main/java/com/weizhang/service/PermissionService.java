package com.weizhang.service;

import com.weizhang.dao.PermissionDao;
import com.weizhang.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {


    @Autowired
    private PermissionDao permissionDao;

    public Permission getRow(int id){
        return permissionDao.getRow(id);
    }

    public Permission getName(String name){
        return permissionDao.getName(name);
    }

    public List<Permission> getList(){
        return permissionDao.getList();
    }

    public int update(Permission permission){
        return permissionDao.update(permission);
    }

    public int insert(Permission permission){
        return permissionDao.insert(permission);
    }
}
