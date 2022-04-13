package com.doromv.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.store.entity.OrderItem;
import com.doromv.store.service.IOrderItemService;
import com.doromv.store.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author DoromvQAQ
* @description 针对表【t_order_item】的数据库操作Service实现
* @createDate 2022-04-13 10:38:57
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements IOrderItemService {

}




