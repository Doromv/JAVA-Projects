package com.doromv.store.controller;

import com.doromv.store.controller.ex.*;
import com.doromv.store.service.ex.*;
import com.doromv.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Doromv
 * @create 2022-03-30-10:18
 */
@RestController
public class BaseController  {
    public static final int OK=200;
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> JsonResult=new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            JsonResult.setState(4000);
            JsonResult.setMessage("用户名被占用");
        }else if(e instanceof InsertException){
            JsonResult.setState(5000);
            JsonResult.setMessage("注册时产生未知的异常");
        }else if(e instanceof UsernameNotFoundException){
            JsonResult.setState(5001);
            JsonResult.setMessage("用户数据不存在");
        } else if(e instanceof PasswordNotMatchExpection){
            JsonResult.setState(5002);
            JsonResult.setMessage("用户的密码错误");
        } else if(e instanceof UpdateException){
            JsonResult.setState(5003);
            JsonResult.setMessage("更新数据时产生未知的异常");
        }else if(e instanceof AccessDeniedException){
            JsonResult.setState(5004);
            JsonResult.setMessage("删除数据时产生了未知异常");
        }else if(e instanceof AddressCountLimitException){
            JsonResult.setState(4003);
            JsonResult.setMessage("用户的收货地址超出上限");
        }else if(e instanceof AddressNotFoundException){
            JsonResult.setState(4004);
            JsonResult.setMessage("收货地址数据不存在的异常");
        } else if(e instanceof AccessDeniedException){
            JsonResult.setState(4005);
            JsonResult.setMessage("收货地址数据非法访问的异常");
        }else if(e instanceof ProductNotFoundException){
            JsonResult.setState(4006);
            JsonResult.setMessage("商品数据不存在的异常");
        }else if(e instanceof FileEmptyException){
            JsonResult.setState(6000);
        }else if(e instanceof FileSizeException){
            JsonResult.setState(6001);
        }else if(e instanceof FileTypeException){
            JsonResult.setState(6002);
        }else if(e instanceof FileStateException){
            JsonResult.setState(6003);
        }else if(e instanceof FileUploadException){
            JsonResult.setState(6004);
        }
        return JsonResult;
    }
    protected final Integer getUidFromSession(HttpSession session){
        return (Integer) session.getAttribute("uid");
    }
    protected final String getUsernameFromSession(HttpSession session){
        return String.valueOf(session.getAttribute("username"));
    }
}
