package com.doromv.store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Doromv
 * @create 2022-04-12-8:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private long price;
    private Integer num;
    private String title;
    private long realPrice;
    private String image;
}
