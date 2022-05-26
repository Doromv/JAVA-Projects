package com.doromv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.pojo.*;
import com.doromv.service.*;
import com.doromv.mapper.OrdersMapper;
import com.doromv.service.ex.AddressBookException;
import com.doromv.service.ex.ShoppingCartException;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
* @author DoromvQAQ
* @description 针对表【orders(订单表)】的数据库操作Service实现
* @createDate 2022-05-26 18:21:06
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    OrderDetailService orderDetailService;
    /**
     * 提交订单
     * @param userId
     * @param orders
     * @return
     */
    @Override
    public ResponseResult<String> saveOrder(Long userId, Orders orders) {

        //查询当前用户的购物车数据
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(wrapper);
        if(ObjectUtils.isEmpty(shoppingCartList)){
            throw new ShoppingCartException("购物车数据为空！");
        }
        //查询用户数据
        User user = userService.getById(userId);
        //查询地址信息
        AddressBook addressBook = addressBookService.getById(orders.getAddressBookId());
        if(ObjectUtils.isEmpty(addressBook)){
            throw new AddressBookException("地址信息不能为空!");
        }
        //计算总金额并且整理订单明细
        Long ordersId = IdWorker.getId();
        AtomicInteger amount=new AtomicInteger(0);
        List<OrderDetail> orderDetails = shoppingCartList.stream()
                .map(i -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderId(ordersId);
                    orderDetail.setNumber(i.getNumber());
                    orderDetail.setDishFlavor(i.getDishFlavor());
                    orderDetail.setDishId(i.getDishId());
                    orderDetail.setSetmealId(i.getSetmealId());
                    orderDetail.setName(i.getName());
                    orderDetail.setImage(i.getImage());
                    orderDetail.setAmount(i.getAmount());
                    amount.addAndGet(i.getAmount().multiply
                            (new BigDecimal(i.getNumber())).intValue());
                    return orderDetail;
                })
                .collect(Collectors.toList());
        //向订单表添加数据
        orders.setNumber(String.valueOf(ordersId));
        orders.setOrderTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(BigDecimal.valueOf(amount.get()));
        orders.setUserId(userId);
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(user.getPhone());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        save(orders);
        //向订单明细表添加数据
        orderDetailService.saveBatch(orderDetails);
        //下单完成之后清空购物车数据
        shoppingCartService.remove(wrapper);

        return ResponseResult.success("下单成功");
    }
}




