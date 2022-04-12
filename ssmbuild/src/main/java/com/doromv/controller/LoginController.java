package com.doromv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Doromv
 * @create 2022-03-07-9:18
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(HttpSession session, Model model,String username,String password){
        session.setAttribute("userInfo",username);
        model.addAttribute("username",username);
        return "allBook";
    }
}
