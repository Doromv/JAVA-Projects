package com.doromv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.dto.DishDto;
import com.doromv.pojo.Dish;
import com.doromv.service.DishFlavorService;
import com.doromv.service.DishService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 修改菜品状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public ResponseResult<String> updateDishStatus(@PathVariable Integer status,@RequestParam("ids") ArrayList<Long> ids){
        return dishService.updateDishStatus(status,ids);
    }

    /**
     * 根据分类查询菜品
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<List<DishDto>> getDishListByCategoryId(Dish dish){

        return dishService.queryDishListByCategoryId(dish);
    }
}
