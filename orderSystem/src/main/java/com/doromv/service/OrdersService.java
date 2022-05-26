package com.doromv.service;

import com.doromv.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.utils.ResponseResult;

/**
* @author DoromvQAQ
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2022-05-26 18:21:07
*/
public interface OrdersService extends IService<Orders> {

    ResponseResult<String> saveOrder(Long userId, Orders orders);
}
