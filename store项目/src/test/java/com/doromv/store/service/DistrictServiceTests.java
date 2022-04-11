package com.doromv.store.service;

import com.doromv.store.entity.Address;
import com.doromv.store.entity.District;
import com.doromv.store.mapper.DistrictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Doromv
 * @create 2022-03-29-19:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;
    @Test
    public void getByParent(){
        for (District district : districtService.getByParent("86")) {
            System.out.println(district );
        }
    }
}
