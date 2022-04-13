package com.doromv.store.service;

import com.doromv.store.entity.Address;
import com.doromv.store.entity.Order;
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
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;
    @Test
    public void create() {
            Integer aid = 10;
            Integer[] cids = {4, 5};
            Integer uid = 19;
            String username = "订单管理员";
            orderService.create(aid, uid, username,cids);
    }
}
