package com.doromv.controller;

import com.doromv.pojo.User;
import com.doromv.service.UserService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Doromv
 * @create 2022-05-25-20:10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 发送验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public ResponseResult<String> sendMsg(HttpSession session, @RequestBody User user){

        String str = userService.sendMsg(user);
        session.setAttribute("phone",user.getPhone());
        session.setAttribute("code",str);
        return ResponseResult.success("已发送验证码");
    }

    /**
     * 用户登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody Map map,HttpSession session){
        //从session中获取验证码和用户手机号
        String code = (String) session.getAttribute("code");
        String phone = (String) session.getAttribute("phone");
        Long userId = userService.login(map, phone, code);
        //将用户id保存到session和线程中
        session.setAttribute("id",userId);
        return ResponseResult.success("登录成功");
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @PostMapping("/loginout")
    public ResponseResult<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("id");
        return ResponseResult.success("退出成功!");
    }
}
