package com.doromv.controller.handleEx;
import com.doromv.service.ex.ShoppingCartException;
import com.doromv.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Doromv
 * @create 2022-05-26-15:10
 * 用来处理ShoppingCartService层抛出的异常
 */
@RestControllerAdvice
public class ShoppingCartControllerAdvice {

    @ExceptionHandler(ShoppingCartException.class)
    public ResponseResult handleException(Exception e){
        return ResponseResult.error(e.getMessage());
    }


}
