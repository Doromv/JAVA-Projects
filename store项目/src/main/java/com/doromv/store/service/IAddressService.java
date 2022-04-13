package com.doromv.store.service;

import com.doromv.store.entity.Address;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-04-8:50
 */
public interface IAddressService {
    void addNewAddress(Integer uid,String username,Address address);
    List<Address> getByUid(Integer uid);
    void setDefault(Integer aid,Integer uid,String username);
    void removeByAid(Integer aid,Integer uid, String username);
    void updateByUid(String tag, String name, String address,
                     String phone, Integer uid, Integer aid,
                     String username, Date date);
    Address getByAid(Integer aid,Integer uid);
}
