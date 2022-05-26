package com.doromv.mapper;

import com.doromv.pojo.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DoromvQAQ
* @description 针对表【shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2022-05-26 15:04:35
* @Entity com.doromv.pojo.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




