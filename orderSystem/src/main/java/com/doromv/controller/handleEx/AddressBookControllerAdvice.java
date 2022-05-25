package com.doromv.controller.handleEx;
import com.doromv.service.ex.AddressBookException;
import com.doromv.service.ex.UserException;
import com.doromv.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Doromv
 * @create 2022-05-23-22:43
 * 用来处理UserService层抛出的异常
 */
@RestControllerAdvice
public class AddressBookControllerAdvice {

    @ExceptionHandler(AddressBookException.class)
    public ResponseResult handleException(Exception e){
        return ResponseResult.error(e.getMessage());
    }


}
