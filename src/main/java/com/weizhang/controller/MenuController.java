package com.weizhang.controller;

import com.weizhang.common.R;
import com.weizhang.model.Menu;
import com.weizhang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取左侧菜单
     * @return
     */

    @RequestMapping(value = "/left-menu")
    @ResponseBody
    public R getLeftMenu(){
        List<Menu> menuList = menuService.getLeftMenu();
        Map<String, Object> map = new HashMap<>();
        map.put("data", menuList);
        return R.success(map);
    }

}
