package com.doromv.store.mapper;
import java.util.Date;
import java.util.List;

import com.doromv.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import com.doromv.store.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DoromvQAQ
* @description 针对表【t_cart】的数据库操作Mapper
* @createDate 2022-04-11 09:42:23
* @Entity com.doromv.store.entity.Cart
*/
public interface CartMapper extends BaseMapper<Cart> {
    int insert(Cart cart);

    int updateNumByCid(@Param("cid") Integer cid,@Param("num") Integer num, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    Cart findByUidAndPid(@Param("uid") Integer uid, @Param("pid") Integer pid);

    List<CartVO> findVOByUid(Integer uid);
}




