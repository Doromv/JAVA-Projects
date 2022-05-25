package com.doromv.service;

import com.doromv.pojo.AddressBook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.utils.ResponseResult;

import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【address_book(地址管理)】的数据库操作Service
* @createDate 2022-05-25 22:30:48
*/
public interface AddressBookService extends IService<AddressBook> {

     ResponseResult<AddressBook> saveAdress(Long userId, AddressBook addressBook);

     ResponseResult<AddressBook> setDefault(Long userId, AddressBook addressBook);

     ResponseResult queryAddress(Long id);

     ResponseResult<AddressBook> queryDefault(Long userId);

     ResponseResult<List<AddressBook>> queryAddressBookList(Long userId,AddressBook addressBook);
}
