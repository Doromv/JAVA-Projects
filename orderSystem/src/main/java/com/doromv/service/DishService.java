package com.doromv.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.dto.DishDto;
import com.doromv.pojo.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.utils.ResponseResult;

/**
* @author DoromvQAQ
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-05-24 10:47:29
*/
public interface DishService extends IService<Dish> {


    ResponseResult<String> saveDish(DishDto dishDto);

    ResponseResult<Page> getDishList(Integer page, Integer pageSize, String name);

    ResponseResult<DishDto> queryDishById(Long id);

    ResponseResult<String> updateDishInfo(DishDto dishDto);
}
