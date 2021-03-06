package com.weizhang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weizhang.common.R;
import com.weizhang.model.Menu;
import com.weizhang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

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



    /**
     * 列表页
     *
     * @return
     */
    @GetMapping(value = "list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "name", defaultValue = "") String name,
                             @RequestParam(value = "start", defaultValue = "") String start,
                             @RequestParam(value = "end", defaultValue = "") String end
    ) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("start", start);
        params.put("end", end);

        PageHelper.startPage(page, PAGESIZE);
        List<Menu> list = menuService.getList();
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("params", params);

        modelAndView.setViewName("menu/list");
        modelAndView.addObject("list", pageInfo);

        return modelAndView;
    }


    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam("id") int id) throws Exception {

        Menu menu = menuService.getRow(id);
        if (menu == null) {
            throw new Exception("Not Found");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("row", menu);
        modelAndView.setViewName("menu/index");
        return modelAndView;
    }

    @GetMapping(value = "add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu/add");
        return modelAndView;
    }

    /**
     * 增加
     *
     * @return
     */
    @PostMapping(value = "doadd")
    @ResponseBody
    public R doAdd(@RequestParam("name") String name,
                   @RequestParam("perm") String perm,
                   @RequestParam("type") int type
    ) {

        Menu menu = new Menu();
        menu.setName(name);
        menu.setType(type);
        int id = menuService.insert(menu);
        if (id > 0) {
            return R.success();
        }
        return R.error();
    }

}
