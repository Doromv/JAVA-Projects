package com.doromv.store.mapper;

import com.doromv.store.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author Doromv
 * @create 2022-04-11-9:58
 */
@SpringBootTest
public class CarMapperTests {
    @Autowired
    CartMapper cartMapper;
    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUid(19);
        cart.setPid(10000013);
        cart.setNum(2);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }
    @Test
    public void updateNumByCid(){
        cartMapper.updateNumByCid(1,4,"Doromv",new Date());
    }
    @Test
    public void findByUidAndPid(){
        System.out.println(cartMapper.findByUidAndPid(19, 10000013));
    }
}
