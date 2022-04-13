package com.doromv.store.controller;

import com.doromv.store.service.ICartService;
import com.doromv.store.service.impl.CartServiceImpl;
import com.doromv.store.util.JsonResult;
import com.doromv.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @RequestMapping({"/",""})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        List<CartVO> data = cartService.getVOByUid(getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("/num/add/{cid}")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer data = cartService.addNum(cid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("/list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,HttpSession session){
        List<CartVO> data = cartService.getVOByCid(cids, getUidFromSession(session));
        return new JsonResult<>(OK,data);
    }
}
