package com.doromv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.dto.OrdersDto;
import com.doromv.pojo.*;
import com.doromv.service.*;
import com.doromv.mapper.OrdersMapper;
import com.doromv.service.ex.AddressBookException;
import com.doromv.service.ex.ShoppingCartException;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.BeanUtils;
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

    /**
     * 查询订单分页
     * @param page
     * @param pageSize
     * @param userId
     * @return
     */
    @Override
    public Page<Orders> queryOrdersPage(Integer page, Integer pageSize, Long userId) {
        //创建分页构造器
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        //创建条件构造器
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        //添加条件
        wrapper.eq(Orders::getUserId,userId)
                .orderByAsc(Orders::getOrderTime);
        //进行分页查询
        page(pageInfo, wrapper);

        return pageInfo;
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
    @Override
    public Page<OrdersDto> queryOrdersDtoPage(Integer page, Integer pageSize,Long number,LocalDateTime beginTime,LocalDateTime endTime) {
        //创建分页构造器
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        //创建条件构造器
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        //添加条件
        wrapper.like(!ObjectUtils.isEmpty(number),Orders::getNumber,number)
                .ge(!ObjectUtils.isEmpty(beginTime), Orders::getOrderTime, beginTime)
                .le(!ObjectUtils.isEmpty(endTime),Orders::getOrderTime,endTime)
                .orderByAsc(Orders::getOrderTime);
        //进行分页查询
        page(pageInfo, wrapper);
        //改造成OrdersDto分页
        Page<OrdersDto> ordersDtoInfo = new Page<>();
        BeanUtils.copyProperties(pageInfo,ordersDtoInfo,"records");
        List<OrdersDto> ordersDtos = pageInfo.getRecords().stream()
                .map(i -> {
                    OrdersDto ordersDto = new OrdersDto();
                    BeanUtils.copyProperties(i, ordersDto);
                    List<OrderDetail> orderDetailList = orderDetailService.query()
                            .eq("order_id", i.getNumber()).list();
                    ordersDto.setOrderDetails(orderDetailList);
                    return ordersDto;
                })
                .collect(Collectors.toList());
        ordersDtoInfo.setRecords(ordersDtos);
        return ordersDtoInfo;
    }

    /**
     * 修改订单状态
     * @param status
     * @param id
     * @return
     */
    @Override
    public ResponseResult<String> updateStatus(Integer status, Long id) {

        update().setSql("status="+status)
                .eq("id",id)
                .update();
        return ResponseResult.success("更新状态成功");
    }
}




