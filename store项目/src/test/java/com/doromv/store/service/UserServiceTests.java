package com.doromv.store.service;

import com.doromv.store.entity.User;
import com.doromv.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.Transient;

/**
 * @author Doromv
 * @create 2022-03-29-19:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService UserService;
    @Test
  public void reg(){
        try {
            User user=new User();
            user.setUsername("test002");
            user.setPassword("123");
            UserService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        User user = UserService.login("user010", "123456");
        System.out.println(user);
    }
    @Test
    public void changePassword(){
        UserService.changePassword(18,"admin","123","123456");
    }
    @Test
    public void getByUid(){
        System.out.println(UserService.getByUid(4));
    }
    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("18905099710");
        user.setEmail("935863060@qq.com");
        user.setGender(0);
        UserService.changeInfo(4,"admin",user);
    }
    @Test
    public void updateAvatar(){
        UserService.changeAvatar(18,"/upload/test.png","管理员");
    }
}
