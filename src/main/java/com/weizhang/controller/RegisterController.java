package com.weizhang.controller;


import com.weizhang.common.R;
import com.weizhang.form.FormUser;
import com.weizhang.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RegisterController {

    @GetMapping(value = "/register/index")
    public String index(){
        return "register/index";
    }

    @PostMapping(value = "/register/register")
    @ResponseBody
    public R register(@Validated FormUser user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return R.error(500, bindingResult.getFieldError().getDefaultMessage());
        }

        return R.success();
    }
}
