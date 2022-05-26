package com.doromv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.pojo.AddressBook;
import com.doromv.service.AddressBookService;
import com.doromv.mapper.AddressBookMapper;
import com.doromv.service.ex.AddressBookException;
import com.doromv.utils.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2022-05-25 22:30:48
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

    /**
     * 添加地址
     * @param userId
     * @param addressBook
     * @return
     */
    @Override
    public ResponseResult<AddressBook> saveAdress(Long userId, AddressBook addressBook) {
        addressBook.setUserId(userId);
        boolean row = save(addressBook);
        if (row==false){
            throw new AddressBookException("添加地址时发生未知异常，请重试！");
        }
        return ResponseResult.success(addressBook);
    }

    /**
     * 修改默认地址
     * @param userId
     * @param addressBook
     * @return
     */
    @Override
    public ResponseResult<AddressBook> setDefault(Long userId, AddressBook addressBook) {
        boolean row = update().setSql("user_id=" + userId).set("is_default", 0).update();
        if(row==false){
            throw new AddressBookException("修改默认地址时发生未知错误，请重试！");
        }
        addressBook.setIsDefault(1);
         row = updateById(addressBook);
        if(row==false){
            throw new AddressBookException("修改默认地址时发生未知错误，请重试！");
        }
        return ResponseResult.success(addressBook);
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Override
    public ResponseResult queryAddress(Long id) {

        AddressBook addressBook = getById(id);
        if(ObjectUtils.isEmpty(addressBook)){
            throw new AddressBookException("该地址不存在！");
        }
        return ResponseResult.success(addressBook);
    }

    /**
     * 根据用户id查询默认地址
     * @param userId
     * @return
     */
    @Override
    public ResponseResult<AddressBook> queryDefault(Long userId) {
        AddressBook addressBook = query().eq("user_id", userId)
                .eq("is_default", 1).one();
        if(ObjectUtils.isEmpty(addressBook)){
            throw new AddressBookException("您还没有设置默认地址！");
        }
        return ResponseResult.success(addressBook);
    }

    /**
     * 查询地址列表
     * @param userId
     * @param addressBook
     * @return
     */
    @Override
    public ResponseResult<List<AddressBook>> queryAddressBookList(Long userId,AddressBook addressBook) {

        addressBook.setUserId(userId);
        List<AddressBook> addressBookList = query().eq(!ObjectUtils.isEmpty(addressBook.getUserId()),
                "user_id", addressBook.getUserId())
                .orderByDesc("update_time").list();
        return ResponseResult.success(addressBookList);
    }

    /**
     * 根据id删除地址
     * @param id
     * @return
     */
    @Override
    public ResponseResult<String> removeAddressBookById(Long id) {
        boolean row = removeById(id);
        if(row==false){
            throw new AddressBookException("删除失败！");
        }
        return ResponseResult.success("删除成功");
    }

    /**
     * 修改地址信息
     * @param addressBook
     * @return
     */
    @Override
    public ResponseResult<String> updateAddressBook(AddressBook addressBook) {

        boolean row = updateById(addressBook);
        if(row==false){
            throw new AddressBookException("更新时发生位置异常，请重试！");
        }
        return ResponseResult.success("更新成功");
    }
}




