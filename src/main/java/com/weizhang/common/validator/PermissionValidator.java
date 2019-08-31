package com.weizhang.common.validator;

import com.weizhang.model.Permission;
import com.weizhang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PermissionValidator implements ConstraintValidator<PermissionExists, String> {


    @Autowired
    private PermissionService permissionService;

    @Override
    public void initialize(PermissionExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Permission permission = permissionService.getName(s);

        if(permission == null){
            return true;
        }
        return false;

    }
}