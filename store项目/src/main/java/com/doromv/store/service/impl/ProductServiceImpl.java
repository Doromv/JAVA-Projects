package com.doromv.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.store.entity.Product;
import com.doromv.store.service.IProductService;
import com.doromv.store.mapper.ProductMapper;
import com.doromv.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【t_product】的数据库操作Service实现
* @createDate 2022-04-10 11:23:45
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements IProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<Product> findHostList() {
        List<Product> list = productMapper.findHostList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedTime(null);
            product.setModifiedTime(null);
        }
        return list;
    }
    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if(product==null){
            throw new ProductNotFoundException();
        }
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedTime(null);
        product.setModifiedTime(null);
        return product;
    }
}




