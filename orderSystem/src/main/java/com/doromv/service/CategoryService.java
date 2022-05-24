package com.doromv.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.utils.ResponseResult;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-05-24 08:50:56
*/
public interface CategoryService extends IService<Category> {

    ResponseResult<String> saveCategory(Category category);

    ResponseResult<Page<Category>> getCategoryInfo(Integer page, Integer pageSize);

    ResponseResult<String> removeCategory(Long id);

    ResponseResult<String> updateCategory(Category category);

    ResponseResult<List<Category>> queryCategoryList(Integer type, Integer sort, LocalDateTime updateTime);
}
