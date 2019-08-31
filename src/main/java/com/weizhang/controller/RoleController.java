package com.weizhang.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weizhang.common.R;
import com.weizhang.model.Role;
import com.weizhang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping( value = "/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

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
        List<Role> list = roleService.getList();
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("params", params);

        modelAndView.setViewName("role/list");
        modelAndView.addObject("list", pageInfo);

        return modelAndView;
    }


    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam("id") int id) throws Exception {

        Role role = roleService.getRow(id);
        if (role == null) {
            throw new Exception("Not Found");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("row", role);
        modelAndView.setViewName("role/index");
        return modelAndView;
    }

    @GetMapping(value = "add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("role/add");
        return modelAndView;
    }

    /**
     * 增加
     *
     * @return
     */
    @PostMapping(value = "doadd")
    @ResponseBody
    public R doAdd(@RequestParam("name") String name) {
        if (roleService.getName(name) != null) {
            return R.error(name + " 存在!");
        }

        Role role = new Role();
        role.setName(name);
        role.setCreatedAt(new Date());
        int id = roleService.inser(role);
        if (id > 0) {
            return R.success();
        }
        return R.error();
    }


}
