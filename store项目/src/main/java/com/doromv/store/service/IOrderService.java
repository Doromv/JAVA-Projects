package com.doromv.store.service;

import com.doromv.store.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author DoromvQAQ
* @description 针对表【t_order】的数据库操作Service
* @createDate 2022-04-13 10:38:56
*/
public interface IOrderService extends IService<Order> {
    Order create(Integer aid,Integer uid,String username,Integer[] cids);
}
