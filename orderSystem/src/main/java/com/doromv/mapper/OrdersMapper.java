package com.doromv.mapper;

import com.doromv.pojo.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DoromvQAQ
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2022-05-26 18:21:06
* @Entity com.doromv.pojo.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




