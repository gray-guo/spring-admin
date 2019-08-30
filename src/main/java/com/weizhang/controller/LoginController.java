package com.weizhang.controller;


import com.weizhang.common.R;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {


    @GetMapping(value = "login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }


    @PostMapping(value = "login")
    @ResponseBody
    public R loginPost(@RequestParam(name = "username") String name, @RequestParam(name = "password") String password) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        try {
            if (subject.isAuthenticated()) {
                return R.success("成功");
            } else {
                return R.error("登录失败");
            }
        } catch (UnknownAccountException e) {
            return R.error("账号不存在");
        } catch (IncorrectCredentialsException e) {
            return R.error("密码错误");
        }
    }

    @RequestMapping(value = "/logout")

    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/index";
    }

}
