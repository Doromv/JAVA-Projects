package com.doromv.store.mapper;

import com.doromv.store.entity.User;
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
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;
    @Test
  public void findHostList(){
        System.out.println(productMapper.findHostList());
    }
}
