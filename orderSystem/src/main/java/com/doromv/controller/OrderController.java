package com.doromv.controller;

import com.doromv.pojo.Orders;
import com.doromv.service.OrderDetailService;
import com.doromv.service.OrdersService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Doromv
 * @create 2022-05-26-18:23
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrdersService ordersService;

    /**
     * 提交订单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public ResponseResult<String> submitOrder(HttpSession session, @RequestBody Orders orders){

        Long userId = (Long) session.getAttribute("id");
        return ordersService.saveOrder(userId,orders);
    }
}
