package com.weizhang.exception;

import com.weizhang.common.R;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class ControllerExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ControllerException.class)
    public R handlerControllerException(ControllerException e){
        log.error(e.getMsg());
        return R.error();
    }

    @ExceptionHandler(NullPointerException.class)
    public R nullPointerException(Exception e){
        log.error(e.getMessage());
        return R.error();
    }


    @ExceptionHandler(Exception.class)
    public R handlerException(Exception e){
        log.error(e.getMessage());
        return R.error();
    }

    @ExceptionHandler(UnauthorizedException.class)
    public R unauthorizedException(Exception e){
        log.error(e.getMessage());
        return R.error();
    }


    @ExceptionHandler(UnauthenticatedException.class)
    public R unauthenticatedException(Exception e){
        log.error(e.getMessage());
        return R.error();
    }



}
