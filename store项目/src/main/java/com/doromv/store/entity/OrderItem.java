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
 * @TableName t_order_item
 */
@TableName(value ="t_order_item")
@Data
public class OrderItem extends BaseEntity implements Serializable {
    /**
     * 订单中的商品记录的id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 所归属的订单的id
     */
    private Integer oid;

    /**
     * 商品的id
     */
    private Integer pid;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 购买数量
     */
    private Integer num;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}