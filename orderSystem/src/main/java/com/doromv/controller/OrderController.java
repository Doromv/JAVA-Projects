package com.doromv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.dto.OrdersDto;
import com.doromv.pojo.Orders;
import com.doromv.service.OrdersService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

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

    /**
     * 查询订单分页
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public ResponseResult<Page<Orders>> getOrdersPage(Integer page,Integer pageSize,HttpSession session){

        Long userId = (Long) session.getAttribute("id");
        Page<Orders> ordersPage = ordersService.queryOrdersPage(page, pageSize, userId);
        return ResponseResult.success(ordersPage);
    }

    /**
     * 查询订单详细分页
     * @param page
     * @param pageSize
     * @param number
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/page")
    public ResponseResult<Page<OrdersDto>> getOrdersDtoPage(Integer page, Integer pageSize, Long number, String beginTime, String endTime){

        Page<OrdersDto> ordersDtoPage = ordersService.queryOrdersDtoPage(page, pageSize, number,beginTime,endTime);
        return ResponseResult.success(ordersDtoPage);
    }

    /**
     * 更改订单状态
     * @param orders
     * @return
     */
    @PutMapping
    public ResponseResult<String> updateStatus(@RequestBody Orders orders){

        Long id = orders.getId();
        Integer status = orders.getStatus();
        return ordersService.updateStatus(status,id);
    }
}
