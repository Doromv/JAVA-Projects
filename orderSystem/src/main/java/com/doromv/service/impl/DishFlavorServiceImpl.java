package com.doromv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.pojo.DishFlavor;
import com.doromv.service.CategoryService;
import com.doromv.service.DishFlavorService;
import com.doromv.mapper.DishFlavorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author DoromvQAQ
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2022-05-24 15:45:16
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{


}




