package com.doromv.store.service;

import com.doromv.store.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【t_product】的数据库操作Service
* @createDate 2022-04-10 11:23:46
*/
public interface IProductService extends IService<Product> {
    List<Product> findHostList();
    Product findById(Integer id);
}
