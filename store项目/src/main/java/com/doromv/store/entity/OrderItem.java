package com.doromv.store.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Doromv
 * @create 2022-04-14-9:27
 */@Data
    public class OrderItem extends BaseEntity implements Serializable {
        private Integer id;
        private Integer oid;
        private Integer pid;
        private String title;
        private String image;
        private Long price;
        private Integer num;

        // Generate: Getter and Setter、Generate hashCode() and equals()、toString()
    }
