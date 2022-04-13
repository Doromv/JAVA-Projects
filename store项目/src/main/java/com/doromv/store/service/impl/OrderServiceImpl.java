package com.doromv.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.store.entity.Address;
import com.doromv.store.entity.Order;
import com.doromv.store.entity.OrderItem;
import com.doromv.store.service.IAddressService;
import com.doromv.store.service.ICartService;
import com.doromv.store.service.IOrderService;
import com.doromv.store.mapper.OrderMapper;
import com.doromv.store.service.ex.InsertException;
import com.doromv.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2022-04-13 10:38:56
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        List<CartVO> list = cartService.getVOByCid(cids, uid);
        Long totalPrice = 0L;
        for (CartVO cartVO : list) {
            totalPrice += cartVO.getPrice() * cartVO.getNum();
        }
        Address address = addressService.getByAid(aid, uid);
        Order order = new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setOrderTime(new Date());
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        int rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException();
        }
        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : list) {
            // 创建订单商品数据
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            // 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(new Date());
            item.setModifiedUser(username);
            item.setModifiedTime(new Date());
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }
            return order;
        }
    }





