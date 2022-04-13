package com.doromv.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.store.entity.Cart;
import com.doromv.store.mapper.ProductMapper;
import com.doromv.store.mapper.UserMapper;
import com.doromv.store.service.ICartService;
import com.doromv.store.mapper.CartMapper;
import com.doromv.store.service.ex.AccessDeniedException;
import com.doromv.store.service.ex.CartNotFoundException;
import com.doromv.store.service.ex.InsertException;
import com.doromv.store.service.ex.UpdateException;
import com.doromv.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【t_cart】的数据库操作Service实现
* @createDate 2022-04-11 09:42:23
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements ICartService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CartMapper cartMapper;
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if(result==null){
            Cart cart = new Cart();
            cart.setPrice(productMapper.findById(pid).getPrice());
            cart.setNum(amount);
            cart.setPid(pid);
            cart.setUid(uid);
            cart.setModifiedTime(new Date());
            cart.setModifiedUser(username);
            cart.setCreatedTime(new Date());
            cart.setCreatedUser(username);
            int rows = cartMapper.insert(cart);
            if(rows!=1){
                throw new InsertException();
            }
        }else {
            int rows = cartMapper.updateNumByCid(result.getCid(), result.getNum() + amount, username, new Date());
            if(rows!=1){
                throw new UpdateException();
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }
    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
         Cart result = cartMapper.findByCid(cid);
         if(result==null){
             throw new CartNotFoundException();
         }
         if(!result.getUid().equals(uid)){
             throw new AccessDeniedException();
         }
        Integer num = result.getNum()+1;
        int rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if(rows!=1){
            throw new UpdateException();
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer[] cids, Integer uid) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        for (CartVO cartVO : list) {
            if(cartVO.getUid()!=uid){
                list.remove(cartVO);
            }
        }
        return list;
    }

}




