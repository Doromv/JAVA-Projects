package com.doromv.service;

import com.doromv.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.utils.ResponseResult;

import java.util.Map;

/**
* @author DoromvQAQ
* @description 针对表【user(用户信息)】的数据库操作Service
* @createDate 2022-05-25 20:09:01
*/
public interface UserService extends IService<User> {

    String sendMsg(User user);

    Long login(Map map,String phone, String code);

}
