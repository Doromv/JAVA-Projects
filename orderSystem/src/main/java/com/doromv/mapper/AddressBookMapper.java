package com.doromv.mapper;

import com.doromv.pojo.AddressBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DoromvQAQ
* @description 针对表【address_book(地址管理)】的数据库操作Mapper
* @createDate 2022-05-25 22:30:48
* @Entity com.doromv.pojo.AddressBook
*/
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}




