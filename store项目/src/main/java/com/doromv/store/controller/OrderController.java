package com.doromv.store.controller;

import com.doromv.store.entity.Order;
import com.doromv.store.service.IOrderService;
import com.doromv.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Doromv
 * @create 2022-04-14-8:13
 */
@RequestMapping("/orders")
@RestController
public class OrderController extends BaseController{
    @Autowired
    IOrderService orderService;
    @RequestMapping("/create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){
        Order data = orderService.create(aid, getUidFromSession(session), getUsernameFromSession(session), cids);
        return new JsonResult<>(OK,data);
    }
}
