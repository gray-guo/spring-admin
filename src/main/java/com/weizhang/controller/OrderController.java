package com.weizhang.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {


    /**
     *
     * @return
     */
    @GetMapping("/order/index")
    @RequiresPermissions("order:index")
    public ModelAndView index(){

        return new ModelAndView("order/index");
    }
}
