package com.doromv.store.mapper;

import com.doromv.store.entity.Order;
import com.doromv.store.entity.OrderItem;
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
public class orderMapperTests {
    @Autowired
    private OrderMapper orderMapper;
    @Test
    public void testInsertOrder(){
        Order order = new Order();
        order.setUid(19);
        order.setRecvName("Doromv");
        order.setRecvPhone("18888888888");
        orderMapper.insertOrder(order);
    }
    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(2);
        orderItem.setTitle("高档铅笔");
        orderMapper.insertOrderItem(orderItem);
    }
}
