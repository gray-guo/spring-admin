package com.weizhang.common.validator;

import com.weizhang.model.User;
import com.weizhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserExistsValidator implements ConstraintValidator<UserExists, String> {

    private String table;

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UserExists constraintAnnotation) {
        table = constraintAnnotation.tablename();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        User user = userService.getUserByName(s);

        if(user == null){
            return true;
        }
        return false;

    }
}
