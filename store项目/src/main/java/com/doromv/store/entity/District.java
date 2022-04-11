package com.doromv.store.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * (TDictDistrict)实体类
 *
 * @author makejava
 * @since 2022-04-04 10:23:00
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class District extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -65266717875412642L;
    
    private Integer id;
    
    private String parent;
    
    private String code;
    
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

