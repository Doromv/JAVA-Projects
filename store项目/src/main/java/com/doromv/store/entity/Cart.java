package com.doromv.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_cart
 */
@TableName(value ="t_cart")
@Data
public class Cart extends BaseEntity implements Serializable {
    /**
     * 购物车数据id
     */
    @TableId(type = IdType.AUTO)
    private Integer cid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 商品id
     */
    private Integer pid;

    /**
     * 加入时商品单价
     */
    private Long price;

    /**
     * 商品数量
     */
    private Integer num;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}