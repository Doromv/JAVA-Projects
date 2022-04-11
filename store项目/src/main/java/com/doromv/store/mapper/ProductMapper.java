package com.doromv.store.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.doromv.store.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DoromvQAQ
* @description 针对表【t_product】的数据库操作Mapper
* @createDate 2022-04-10 11:23:45
* @Entity com.doromv.store.entity.Product
*/
public interface ProductMapper extends BaseMapper<Product> {
    List<Product> findHostList();
    Product findById(@Param("id") Integer id);
}




