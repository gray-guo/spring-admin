package com.weizhang.controller.user;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weizhang.common.R;
import com.weizhang.form.FormUser;
import com.weizhang.model.User;
import com.weizhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/user")
public class AdminUserController {


    @Autowired
    private UserService userService;


    /**
     * 列表页
     * @return
     */
    @GetMapping(value = "list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "username", defaultValue = "" ) String username,
                             @RequestParam(value = "start", defaultValue = "") String start,
                             @RequestParam(value = "end", defaultValue = "") String end
                             ) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("start", start);
        params.put("end", end);

        PageHelper.startPage(page, 2);
        List<User> list = userService.getList();
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("params", params);

        modelAndView.setViewName("admin/user/list");
        modelAndView.addObject("list", pageInfo);

        return modelAndView;
    }

    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @GetMapping(value = "add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user/add");
        return modelAndView;
    }

    /**
     * 增加
     * @return
     */
    @PostMapping(value = "doadd")
    @ResponseBody
    public R doAdd(@Validated FormUser user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return R.success();
    }

    /**
     * 修改
     * @return
     */
    public R modify(){
        return R.success();
    }

}
