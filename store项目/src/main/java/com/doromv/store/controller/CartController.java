package com.doromv.store.controller;

import com.doromv.store.service.ICartService;
import com.doromv.store.service.impl.CartServiceImpl;
import com.doromv.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Doromv
 * @create 2022-04-11-11:17
 */
@RestController
@RequestMapping("/carts")
public class CartController extends BaseController {
    @Autowired
    ICartService cartService;
    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        cartService.addToCart(
                getUidFromSession(session),
                pid,
                amount,
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
