package com.weizhang.service;


import com.weizhang.common.MenuTree;
import com.weizhang.dao.MenuDao;
import com.weizhang.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {


    @Autowired
    private MenuDao menuDao;

    public List<Menu> getList() {
        return menuDao.getList();
    }

    public Menu getRow(int id) {
        return menuDao.getRow(id);
    }


    public Menu getUserByName(String name) {
        return menuDao.getUserByName(name);
    }


    public int insert(Menu menu){
        return menuDao.insert(menu);
    }


    public List<Menu> getLeftMenu() {
        List<Menu> list = menuDao.getList();
        MenuTree menuTree = new MenuTree(list);
        return menuTree.builTree();
    }

}
