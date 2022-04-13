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
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
public class Order extends BaseEntity implements Serializable {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Integer oid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 收货人姓名
     */
    private String recvName;

    /**
     * 收货人电话
     */
    private String recvPhone;

    /**
     * 收货人所在省
     */
    private String recvProvince;

    /**
     * 收货人所在市
     */
    private String recvCity;

    /**
     * 收货人所在区
     */
    private String recvArea;

    /**
     * 收货详细地址
     */
    private String recvAddress;

    /**
     * 总价
     */
    private Long totalPrice;

    /**
     * 状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
     */
    private Integer status;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 支付时间
     */
    private Date payTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}