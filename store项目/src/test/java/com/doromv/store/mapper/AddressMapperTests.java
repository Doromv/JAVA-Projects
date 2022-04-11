package com.doromv.store.mapper;

import com.doromv.store.entity.Address;
import com.doromv.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-03-29-19:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;
    @Test
  public void insert(){
        Address address=new Address();
        address.setUid(1);
        address.setPhone("189");
        address.setName("Doromv");
        Integer rows = addressMapper.insert(address);
        System.out.println(rows);
    }
    @Test
    public void countByUid(){
        System.out.println(addressMapper.countByUid(1));
    }
    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(19);
        for (Address address : list) {
            System.out.println(address);
        }
    }
    @Test
    public void findByAid(){
        System.out.println(addressMapper.findByAid(10));
    }
    @Test
    public void updateDefaultByAid(){
      addressMapper.updateDefaultByAid(10,"Doromv",new Date());
    }
    @Test
    public void updateNonDefault(){
       addressMapper.updateNonDefault(19);
    }
    @Test
    public void deleteAddress(){
        addressMapper.deleteByAid(12);
    }
    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(19));
    }
    @Test
    public void updateByUid(){
        Address address = new Address();
        address.setAid(15);
        address.setName("桥本环奈");
        address.setAddress("创世纪");
        address.setPhone("12313145");
        address.setTag("公司");
        System.out.println(addressMapper.updateByUid(address));
    }
}
