package com.doromv.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.dto.SetmealDto;
import com.doromv.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.utils.ResponseResult;

import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2022-05-24 10:47:29
*/
public interface SetmealService extends IService<Setmeal> {

     ResponseResult<String> saveSetmeal(SetmealDto setmealDto);

    ResponseResult<Page> querySetmealList(Integer page, Integer pageSize, String name);

    ResponseResult<String> removeSetmeals(List<Long> ids);

    ResponseResult<String> updateStatus(Integer status,List<Long> ids);

    ResponseResult<SetmealDto> getSetmealById(Long id);

    ResponseResult<String> updateSetmeal(SetmealDto setmealDto);

    ResponseResult<List<Setmeal>> querySetmealListByCategoryId(Setmeal setmeal);
}
