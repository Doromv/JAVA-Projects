package com.doromv.store.service.impl;

import com.doromv.store.entity.User;
import com.doromv.store.mapper.UserMapper;
import com.doromv.store.service.IUserService;
import com.doromv.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author Doromv
 * @create 2022-03-30-8:21
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        User result = userMapper.findByUsername(user.getUsername());
        if(result!=null){
            throw new UsernameDuplicatedException();
        }
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);
        user.setIsDelete(0);
        user.setSalt(salt);
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        Integer rows = userMapper.insert(user);
        if(rows!=1){
            throw new InsertException();
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if(result==null){
            throw new UsernameNotFoundException();
        }
        if(result.getIsDelete()==1){
            throw new UsernameNotFoundException();
        }
        String salt = result.getSalt();
        String oldPassword = result.getPassword();
        String md5Password = getMD5Password(password, salt);
        if(!md5Password.equals(oldPassword)){
            throw new PasswordNotMatchExpection();
        }
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException();
        }
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if(!oldMd5Password.equals(result.getPassword())){
            throw new PasswordNotMatchExpection();
        }
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows!=1){
            throw new UpdateException();
        }
    }

    @Override
    public User getByUid(Integer id) {
        User result = userMapper.findByUid(id);
        if(result==null||result.getIsDelete()==1) {
            throw new UsernameNotFoundException();
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1) {
            throw new UsernameNotFoundException();
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException();
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avater, String username) {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException();
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avater, username, new Date());
        if(rows!=1){
            throw new UpdateException();
        }
    }

    private String getMD5Password(String password,String salt){
        for(int i=0;i<3;i++){
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
