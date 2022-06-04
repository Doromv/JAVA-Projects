package com.doromv.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.dto.OrdersDto;
import com.doromv.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.utils.ResponseResult;

import java.time.LocalDateTime;

/**
* @author DoromvQAQ
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2022-05-26 18:21:07
*/
public interface OrdersService extends IService<Orders> {

    ResponseResult<String> saveOrder(Long userId, Orders orders);

    Page<Orders> queryOrdersPage(Integer page, Integer pageSize, Long userId);

    Page<OrdersDto> queryOrdersDtoPage(Integer page, Integer pageSize, Long number, String beginTime, String endTime);

    ResponseResult<String> updateStatus(Integer status, Long id);
}
