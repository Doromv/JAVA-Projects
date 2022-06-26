package com.doromv.dto;


import com.doromv.pojo.OrderDetail;
import com.doromv.pojo.Orders;
import lombok.Data;

import java.util.List;


@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    //收餐人
    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
