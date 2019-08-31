package com.weizhang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weizhang.common.R;
import com.weizhang.model.Permission;
import com.weizhang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

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
        List<Permission> list = permissionService.getList();
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("params", params);

        modelAndView.setViewName("permission/list");
        modelAndView.addObject("list", pageInfo);

        return modelAndView;
    }


    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam("id") int id) throws Exception {

        Permission permission = permissionService.getRow(id);
        if (permission == null) {
            throw new Exception("Not Found");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("row", permission);
        modelAndView.setViewName("permission/index");
        return modelAndView;
    }

    @GetMapping(value = "add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("permission/add");
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

        Permission permission = new Permission();
        permission.setName(name);
        permission.setPerm(perm);
        permission.setType(type);
        permission.setCratedAt(new Date());
        int id = permissionService.insert(permission);
        if (id > 0) {
            return R.success();
        }
        return R.error();
    }
}
