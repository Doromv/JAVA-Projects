package com.doromv.store.mapper;
import com.doromv.store.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import com.doromv.store.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DoromvQAQ
* @description 针对表【t_order】的数据库操作Mapper
* @createDate 2022-04-13 10:38:56
* @Entity com.doromv.store.entity.Order
*/
public interface OrderMapper extends BaseMapper<Order> {
    int insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);
}




