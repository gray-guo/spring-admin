package com.weizhang.form;

import com.weizhang.common.validator.UserExists;


public class FormUser {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @UserExists(message = "用户名字存在")
    private String name;
}
