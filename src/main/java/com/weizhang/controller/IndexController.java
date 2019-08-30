package com.weizhang.controller;


import com.weizhang.model.User;
import com.weizhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        List<User> list = userService.getList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
