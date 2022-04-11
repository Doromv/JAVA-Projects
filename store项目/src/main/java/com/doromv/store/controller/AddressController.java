package com.doromv.store.controller;

import com.doromv.store.entity.Address;
import com.doromv.store.service.IAddressService;
import com.doromv.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.rmi.server.UID;
import java.util.Date;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-04-8:50
 */
@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;
    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        addressService.addNewAddress(getUidFromSession(session),getUsernameFromSession(session),address);
        return new JsonResult<>(OK);
    }
    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("/set_default/{aid}")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session){
        addressService.setDefault(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("/delete/{aid}")
    public JsonResult<Void> removeByAid(@PathVariable("aid") Integer aid,HttpSession session){
        addressService.removeByAid(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("/update/{tag}/{name}/{address}/{phone}/{aid}")
    public JsonResult<Void> updateByUid(@PathVariable("aid")Integer aid,@PathVariable("tag") String tag,@PathVariable("name") String name,@PathVariable("address") String address,@PathVariable("phone") String phone,HttpSession session){
        addressService.updateByUid(tag,name,address,phone,getUidFromSession(session),aid,getUsernameFromSession(session),new Date());
        return new JsonResult<>(OK);
    }
}
