package com.doromv.controller;

import com.doromv.pojo.AddressBook;
import com.doromv.service.AddressBookService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-05-25-22:33
 */
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    /**
     * 新增地址
     * @param session
     * @param addressBook
     * @return
     */
    @PostMapping
    public ResponseResult<AddressBook> saveAdress(HttpSession session, @RequestBody AddressBook addressBook){
        Long userId = (Long) session.getAttribute("id");
        return addressBookService.saveAdress(userId,addressBook);
    }

    /**
     * 修改默认地址
     * @param session
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    public ResponseResult<AddressBook> setDefault(HttpSession session, @RequestBody AddressBook addressBook){
        Long userId = (Long) session.getAttribute("id");
        return addressBookService.setDefault(userId,addressBook);
    }

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getAddress(@PathVariable Long id){

        return addressBookService.queryAddress(id);
    }

    /**
     * 查询默认地址
     * @param session
     * @return
     */
    @GetMapping("/default")
    public ResponseResult<AddressBook> getDefault(HttpSession session){

        Long userId = (Long) session.getAttribute("id");
        return addressBookService.queryDefault(userId);
    }

    /**
     * 查询地址列表
     * @param addressBook
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<List<AddressBook>> getAddressBookList(HttpSession session,AddressBook addressBook){

        Long userId = (Long) session.getAttribute("id");
        return addressBookService.queryAddressBookList(userId,addressBook);
    }

    /**
     * 根据id删除地址
     * @param id
     * @return
     */
    @DeleteMapping
    public ResponseResult<String> deleteAddressBookById(@RequestParam("ids") Long id){

        return addressBookService.removeAddressBookById(id);
    }

    /**
     * 更新地址信息
     * @param addressBook
     * @return
     */
    @PutMapping
    public ResponseResult<String> updateAddressBook(@RequestBody AddressBook addressBook){

        return addressBookService.updateAddressBook(addressBook);
    }
}
