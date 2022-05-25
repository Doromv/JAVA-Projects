package com.doromv.mapper;

import com.doromv.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DoromvQAQ
* @description 针对表【user(用户信息)】的数据库操作Mapper
* @createDate 2022-05-25 20:09:01
* @Entity com.doromv.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




