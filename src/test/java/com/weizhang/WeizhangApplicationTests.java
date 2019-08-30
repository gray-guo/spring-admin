package com.weizhang;

import com.weizhang.model.Menu;
import com.weizhang.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeizhangApplicationTests {

    @Autowired
    private MenuService menuService;


    @Test
    public void menu() {

        List<Menu> menuList = menuService.getLeftMenu();
        System.out.println(menuList.size());
        for (Menu menu : menuList){
            System.out.println(menu.getUrl());
        }
    }

}
