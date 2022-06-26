package com.doromv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.pojo.ShoppingCart;
import com.doromv.service.ShoppingCartService;
import com.doromv.mapper.ShoppingCartMapper;
import com.doromv.service.ex.ShoppingCartException;
import com.doromv.service.ex.UserException;
import com.doromv.utils.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-05-26 15:04:35
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

    /**
     * 添加菜品或套餐到购物车
     * @param shoppingCart
     * @return
     */
    @Override
    public ResponseResult<ShoppingCart> saveShop(Long userId,ShoppingCart shoppingCart) {
        if(ObjectUtils.isEmpty(userId)){

            throw new UserException("请登录！");
        }
        //将用户id保存到shoppingCart中
        shoppingCart.setUserId(userId);
        //查询购物车中是否已添加该菜品
        ShoppingCart shop = query().eq("user_id", userId)
                .eq(!ObjectUtils.isEmpty(shoppingCart.getDishId()),
                        "dish_id", shoppingCart.getDishId())
                .eq(!ObjectUtils.isEmpty(shoppingCart.getSetmealId()),
                        "setmeal_id", shoppingCart.getSetmealId())
                .one();
        if(!ObjectUtils.isEmpty(shop)){
            shop.setNumber(shop.getNumber()+1);
            boolean row = updateById(shop);
            if(row==false){
                throw new ShoppingCartException("添加菜品到购物车时发生未知异常，请重试！");
            }
            return ResponseResult.success(shop);
        }
        boolean row = save(shoppingCart);
        if(row==false){
            throw new ShoppingCartException("添加菜品到购物车时发生未知异常，请重试！");
        }
        return ResponseResult.success(shoppingCart);
    }

    @Override
    public ResponseResult<List<ShoppingCart>> showShopList(Long userId) {
        List<ShoppingCart> shoppingCartList = query()
                .eq("user_id", userId)
                .orderByAsc("create_time")
                .list();
        return ResponseResult.success(shoppingCartList);
    }

    /**
     * 根据用户id清空购物车
     * @param userId
     * @return
     */
    @Override
    public ResponseResult<String> cleanShopCartById(Long userId) {

        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId,userId);
        boolean row = remove(wrapper);
        if(row==false){
            throw new ShoppingCartException("清空购物车时发生未知异常，请重试");
        }
        return ResponseResult.success("清空成功");
    }

    /**
     * 减少一个菜品
     * @param userId
     * @param shoppingCart
     * @return
     */
    @Override
    public ResponseResult<ShoppingCart> subShop(Long userId, ShoppingCart shoppingCart) {
        //将用户id保存到shoppingCart中
        shoppingCart.setUserId(userId);
        //查询购物车中是否已添加该菜品
        ShoppingCart shop = query().eq("user_id", userId)
                .eq(!ObjectUtils.isEmpty(shoppingCart.getDishId()),
                        "dish_id", shoppingCart.getDishId())
                .eq(!ObjectUtils.isEmpty(shoppingCart.getSetmealId()),
                        "setmeal_id", shoppingCart.getSetmealId())
                .one();
        if(!ObjectUtils.isEmpty(shop)){
            shop.setNumber(shop.getNumber()-1);
            if(shop.getNumber()==0){
                boolean row = removeById(shop);
                if(row==false){
                    throw new ShoppingCartException("减少菜品到购物车时发生未知异常，请重试！");
                }
                return ResponseResult.success(shop);
            }
            boolean row = updateById(shop);
            if(row==false){
                throw new ShoppingCartException("减少菜品到购物车时发生未知异常，请重试！");
            }
            return ResponseResult.success(shop);
        }
        return ResponseResult.success(shop);
    }

}




