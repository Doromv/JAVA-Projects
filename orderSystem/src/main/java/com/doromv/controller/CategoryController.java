package com.doromv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.pojo.Category;
import com.doromv.pojo.Dish;
import com.doromv.pojo.Setmeal;
import com.doromv.service.CategoryService;
import com.doromv.service.SetmealService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-05-24-8:56
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 新增类型
     * @param category 前端数据传来的类型信息
     * @return
     */
    @PostMapping
    public ResponseResult<String> saveCategory(@RequestBody Category category){

        return categoryService.saveCategory(category);
    }

    /**
     * 展示分类信息
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public ResponseResult<Page<Category>> showCategoryInfo(Integer page, Integer pageSize){

        return categoryService.getCategoryInfo(page,pageSize);
    }

    /**
     * 删除分类
     * @param id 前端传来的分类id
     * @return
     */
    @DeleteMapping
    public ResponseResult<String> deleteCategory(Long id){

        return categoryService.removeCategory(id);
    }

    /**
     * 修改分类信息
     * @param category 前端传来的分类信息
     * @return
     */
    @PutMapping
    public ResponseResult<String> modifyCategory(@RequestBody Category category){

        return categoryService.updateCategory(category);
    }

    /**
     * 根据条件查询分类数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<List<Category>> getCategoryList(Category category){
        //1.获取type的值
        Integer type = category.getType();
        //2.获取sort的值
        Integer sort = category.getSort();
        //3.获取更新时间
        LocalDateTime updateTime = category.getUpdateTime();
        return categoryService.queryCategoryList(type,sort,updateTime);
    }


}
