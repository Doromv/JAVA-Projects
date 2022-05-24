package com.doromv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.mapper.CategoryMapper;
import com.doromv.pojo.Category;
import com.doromv.service.CategoryService;
import com.doromv.service.DishService;
import com.doromv.service.SetmealService;
import com.doromv.service.ex.CategoryException;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author DoromvQAQ
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-05-24 08:50:56
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;

    /**
     * 新增类型
     * @param category
     * @return
     */
    @Override
    public ResponseResult<String> saveCategory(Category category) {
        //1.判断是否已存在该类型
        Integer count= query().eq("name", category.getName()).count();
        if(count!=0){
            throw new CategoryException("该类型已存在！");
        }
        boolean row = save(category);
        //2.检验是否更新成功
        if(row==false){
            throw new CategoryException("存储数据时发生位置异常，请重试！");
        }
        return ResponseResult.success("保存成功");
    }

    @Override
    public ResponseResult<Page<Category>> getCategoryInfo(Integer page, Integer pageSize) {

        //1.创建分页构造器
        Page<Category> pageInfo = new Page<>();
        //2.创建条件构造器
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        //3.添加条件
        wrapper.orderByAsc(Category::getSort);
        //4.进行分页查询
        page(pageInfo,wrapper);
        return ResponseResult.success(pageInfo);
    }

    /**
     * 删除分类
     * @param id 前端传来的分类id
     * @return
     */
    @Override
    public ResponseResult<String> removeCategory(Long id) {

        //1.查询该分类是否关联了菜品
        Integer count = dishService.query().eq("category_id", id).count();
        if(count!=0){
            throw new CategoryException("当前分类还有菜品关联，不能删除!");
        }
        //2.查询该分类是否关联了套餐
         count = setmealService.query().eq("category_id", id).count();
        if(count!=0){
            throw new CategoryException("当前分类还有套餐关联，不能删除!");
        }
        removeById(id);
        return ResponseResult.success("分类删除成功");
    }

    /**
     * 修改分类信息
     * @param category 前端传来的分类信息
     * @return
     */
    @Override
    public ResponseResult<String> updateCategory(Category category) {
        //检查分类名是否已被使用
        Integer count = query().eq("name", category.getName()).count();
        if(count!=0){
            throw new CategoryException("该分类名已被使用！");
        }
        boolean row = updateById(category);
        //检查是否更新成功
        if(row==false){
            throw new CategoryException("更新数据时发生位置异常，请重新修改！");
        }
        //返回结果
        return ResponseResult.success("修改成功");
    }

    @Override
    public ResponseResult<List<Category>> queryCategoryList
            (Integer type, Integer sort, LocalDateTime updateTime) {
        //1.检查type是否为空
        if(ObjectUtils.isEmpty(type)){
            throw new CategoryException("type不能为空！");
        }
        //2.查询分类信息
        List<Category> categoryList = query().eq("type", type)
                .orderByAsc(!ObjectUtils.isEmpty(sort),String.valueOf(sort))
                .orderByAsc(!ObjectUtils.isEmpty(updateTime),String.valueOf(updateTime)).list();
        return ResponseResult.success(categoryList);
    }
}




