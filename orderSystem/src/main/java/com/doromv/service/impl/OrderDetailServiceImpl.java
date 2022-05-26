package com.doromv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.pojo.OrderDetail;
import com.doromv.service.OrderDetailService;
import com.doromv.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author DoromvQAQ
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-05-26 18:21:07
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




