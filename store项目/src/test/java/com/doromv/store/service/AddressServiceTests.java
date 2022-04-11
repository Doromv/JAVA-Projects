package com.doromv.store.service;

import com.doromv.store.entity.Address;
import com.doromv.store.entity.User;
import com.doromv.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Doromv
 * @create 2022-03-29-19:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;
    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("157");
        address.setName("z");
        addressService.addNewAddress(1,"admin",address);
    }
    @Test
    public void getByUid(){
        System.out.println(addressService.getByUid(19));
    }
    @Test
    public void setDefault(){
       addressService.setDefault(9,19,"Doromv");
    }
    @Test
    public void removeByAid(){
        addressService.removeByAid(14,19,"Doromv");
    }
    @Test
    public void updateByUid(){
        addressService.updateByUid("tag", "name","address", "phone", 19, 16 , "username", new Date());
    }
}
