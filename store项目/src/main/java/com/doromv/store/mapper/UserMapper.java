package com.doromv.store.mapper;

import com.doromv.store.entity.User;

import java.util.Date;

/**
 * @author Doromv
 * @create 2022-03-29-19:17
 */
public interface UserMapper {
    Integer insert(User user);
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);
    User findByUsername(String username);
    User findByUid(Integer uid);
    Integer updateInfoByUid(User user);
    Integer updateAvatarByUid(Integer uid,String avater,String modifiedUser,Date modifiedTime);
}
