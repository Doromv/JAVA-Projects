package com.doromv.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Doromv
 * @create 2022-04-11-11:08
 */
@SpringBootTest
public class CartServiceTests {
    @Autowired
    ICartService cartService;
    @Test
    public void addToCart(){
        cartService.addToCart(19,10000006,4,"Doromv");
    }
}
