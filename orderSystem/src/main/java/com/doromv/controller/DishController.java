package com.doromv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.dto.DishDto;
import com.doromv.pojo.Dish;
import com.doromv.service.DishFlavorService;
import com.doromv.service.DishService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜品管理
 * @author Doromv
 * @create 2022-05-24-15:48
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /***
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public ResponseResult<String> saveDish(@RequestBody DishDto dishDto){

        return dishService.saveDish(dishDto);
    }

    /**
     * 菜品列表查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public ResponseResult<Page> showDishList(Integer page,Integer pageSize,String name){

        return dishService.getDishList(page,pageSize,name);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult<DishDto> getDishById(@PathVariable Long id){

        return dishService.queryDishById(id);
    }

    /**
     * 修改菜品的信息（包括口味信息）
     * @param dishDto
     * @return
     */
    @PutMapping
    public ResponseResult<String> updateDishInfo(@RequestBody DishDto dishDto){

        return dishService.updateDishInfo(dishDto);
    }
}
