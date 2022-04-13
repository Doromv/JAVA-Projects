package com.doromv.store.service.impl;

import com.doromv.store.entity.Address;
import com.doromv.store.mapper.AddressMapper;
import com.doromv.store.service.IAddressService;
import com.doromv.store.service.IDistrictService;
import com.doromv.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-04-9:05
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer maxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer result = addressMapper.countByUid(uid);
        if(result>=maxCount){
            throw new AddressCountLimitException();
        }
        String provinceName = districtService.getNameBycode(address.getProvinceCode());
        String cityName = districtService.getNameBycode(address.getCityCode());
        String areaName = districtService.getNameBycode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        if(result==0){
            address.setIsDefault(1);
        }else {
            address.setIsDefault(0);
        }
        address.setUid(uid);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException();
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        List<Address> newList=new ArrayList<>();
        for (Address address : list) {
            Address newAddress = new Address();
            newAddress.setAid(address.getAid());
            newAddress.setUid(address.getUid());
            newAddress.setTag(address.getTag());
            newAddress.setName(address.getName());
            newAddress.setAddress(address.getAddress());
            newAddress.setPhone(address.getPhone());
            newAddress.setProvinceName(address.getProvinceName());
            newAddress.setCityName(address.getCityName());
            newAddress.setAreaName(address.getAreaName());
            newAddress.setZip(address.getZip());
            newList.add(newAddress);
        }
        return newList;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException();
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException();
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException();
        }
         rows = addressMapper.updateDefaultByAid(aid,username,new Date());
        if(rows!=1){
            throw new UpdateException();
        }
    }

    @Override
    public void removeByAid(Integer aid,Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException();
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException();
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteException();
        }
        Integer count = addressMapper.countByUid(uid);
        if(count==0){
            return;
        }
        if(result.getIsDefault()==1){
            Address address = addressMapper.findLastModified(uid);
            rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        }
        if(rows!=1){
            throw new UpdateException();
        }
    }

    @Override
    public void updateByUid(String tag, String name, String address, String phone, Integer uid, Integer aid, String username, Date date) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException();
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException();
        }
        Address address1 = new Address();
        address1.setAddress(address);
        address1.setName(name);
        address1.setTag(tag);
        address1.setPhone(phone);
        address1.setUid(uid);
        address1.setAid(aid);
        address1.setModifiedUser(username);
        address1.setModifiedTime(date);
        Integer rows = addressMapper.updateByUid(address1);
        if(rows!=1){
            throw new UpdateException();
        }
    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException();
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException();
        }
        result.setProvinceCode(null);
        result.setCityCode(null);
        result.setAreaCode(null);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);
        return result;
    }
}
