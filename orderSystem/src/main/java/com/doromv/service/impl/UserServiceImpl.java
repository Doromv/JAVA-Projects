package com.doromv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.pojo.User;
import com.doromv.service.UserService;
import com.doromv.mapper.UserMapper;
import com.doromv.service.ex.UserException;
import com.doromv.utils.ResponseResult;
import com.doromv.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
* @author DoromvQAQ
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2022-05-25 20:09:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    /**
     * 获取验证码并且返回
     * @param user
     * @return
     */
    @Override
    public String sendMsg(User user) {
        //1.获取手机号
        String phone = user.getPhone();
        if(ObjectUtils.isEmpty(phone)){
            throw new UserException("号码不能为空！");
        }
        //2.生成一个随机验证码
        Integer integer = ValidateCodeUtils.generateValidateCode(6);
        //3.将验证码返回给用户
        System.out.println(integer);
        //4。返回验证码
        return integer.toString();
    }

    /**
     * 将用户号码保存到数据库，并返回用户id
     * @param map
     * @param phone
     * @param code
     * @return
     */
    @Override
    public Long login(Map map,String phone, String code) {
        //1.获取手机号
        String user_phone = (String) map.get("phone");
        if(!user_phone.equals(phone)){
            throw new UserException("请输入正确的手机号！");
        }
        //2.获取验证码
        String user_code = (String) map.get("code");
        if(!user_code.equals(code)){
            throw new UserException("请输入正确的验证码！");
        }
        //3.将用户信息保存到数据库
        //检查数据库是否已有该用户
        User user = query().eq("phone", phone).one();
        if(!ObjectUtils.isEmpty(user)){
            return user.getId();
        }
        user = new User();
        user.setPhone(phone);
        boolean row = save(user);
        if(row==false){
            throw new UserException("发生未知异常，请重新获取验证码！");
        }
        return user.getId();
    }
}




