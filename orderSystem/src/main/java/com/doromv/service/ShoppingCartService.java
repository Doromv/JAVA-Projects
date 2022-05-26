package com.doromv.service;

import com.doromv.pojo.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.utils.ResponseResult;

import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【shopping_cart(购物车)】的数据库操作Service
* @createDate 2022-05-26 15:04:35
*/
public interface ShoppingCartService extends IService<ShoppingCart> {

    ResponseResult<ShoppingCart> saveShop(Long userId,ShoppingCart shoppingCart);

    ResponseResult<List<ShoppingCart>> showShopList(Long userId);

    ResponseResult<String> cleanShopCartById(Long userId);

    ResponseResult<ShoppingCart> subShop(Long userId, ShoppingCart shoppingCart);
}
