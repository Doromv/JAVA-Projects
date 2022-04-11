package com.doromv.store.mapper;

import com.doromv.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Doromv
 * @create 2022-03-29-19:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
  public void insert(){
        User user=new User();
        user.setUsername("Doromv");
        user.setPassword("QAQ");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("Doromv");
        System.out.println(user);
    }
    @Test
    public void findByUid(){
        User user = userMapper.findByUid(01);
        System.out.println(user);
    }
    @Test
    public void updatePasswordByUid(){
        Integer rows = userMapper.updatePasswordByUid(
                01,
                "123",
                "admin",
                new Date());
        System.out.println(rows);
    }
    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(4);
        user.setPhone("189");
        user.setEmail("DoromvQAQ");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(18,"/upload/avater.png","admin",new Date());
    }
}
