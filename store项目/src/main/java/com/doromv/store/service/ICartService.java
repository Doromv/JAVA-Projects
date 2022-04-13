package com.doromv.store.service;

import com.doromv.store.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.store.vo.CartVO;

import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【t_cart】的数据库操作Service
* @createDate 2022-04-11 09:42:23
*/
public interface ICartService extends IService<Cart> {
    void addToCart(Integer uid,Integer pid,Integer amount,String username);

    List<CartVO> getVOByUid(Integer uid);

    Integer addNum(Integer cid,Integer uid,String username);

    List<CartVO> getVOByCid(Integer[] cids,Integer uid);


}
