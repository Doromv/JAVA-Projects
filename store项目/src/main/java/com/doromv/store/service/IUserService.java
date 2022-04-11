package com.doromv.store.service;

import com.doromv.store.entity.User;

/**
 * @author Doromv
 * @create 2022-03-30-8:20
 */
public interface IUserService {
    void reg(User user);
    User login(String username,String password);
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);
    User getByUid(Integer id);
    void changeInfo(Integer uid,String username,User user);
    void changeAvatar(Integer uid,String avater,String username);
}
