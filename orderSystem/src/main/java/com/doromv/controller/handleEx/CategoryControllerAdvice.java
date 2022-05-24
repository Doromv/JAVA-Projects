package com.doromv.controller.handleEx;
import com.doromv.service.ex.CategoryException;
import com.doromv.service.ex.EmployeeException;
import com.doromv.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Doromv
 * @create 2022-05-23-12:06
 * 用来处理CategoryService层抛出的异常
 */
@RestControllerAdvice
public class CategoryControllerAdvice {

    @ExceptionHandler(CategoryException.class)
    public ResponseResult handleException(Exception e){
        return ResponseResult.error(e.getMessage());
    }


}
