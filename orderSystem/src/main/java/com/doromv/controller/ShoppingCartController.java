package com.doromv.controller;

import com.doromv.pojo.ShoppingCart;
import com.doromv.service.ShoppingCartService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-05-26-15:06
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * 添加菜品或套餐到购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public ResponseResult<ShoppingCart> saveShop(HttpSession session, @RequestBody ShoppingCart shoppingCart){

        //获取当前用户的id
        Long userId = (Long) session.getAttribute("id");
        return shoppingCartService.saveShop(userId,shoppingCart);
    }

    /**
     * 减少一个菜品
     * @param session
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    public ResponseResult<ShoppingCart> subShop(HttpSession session, @RequestBody ShoppingCart shoppingCart){

        //获取当前用户的id
        Long userId = (Long) session.getAttribute("id");
        return shoppingCartService.subShop(userId,shoppingCart);
    }
    /**
     * 通过用户id获取菜品列表
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<List<ShoppingCart>> showShopList(HttpSession session){

        Long userId = (Long) session.getAttribute("id");
        return shoppingCartService.showShopList(userId);
    }

    /**
     * 清空购物车
     * @param session
     * @return
     */
    @DeleteMapping("/clean")
    public ResponseResult<String> cleanShopCart(HttpSession session){
        Long userId = (Long) session.getAttribute("id");
        return shoppingCartService.cleanShopCartById(userId);
    }
}
