package com.doromv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.dto.SetmealDto;
import com.doromv.service.SetmealDishService;
import com.doromv.service.SetmealService;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-05-25-8:42
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 保存套餐信息
     * @param setmealDto
     * @return
     */
    @PostMapping
    public ResponseResult<String> saveSetmeal(@RequestBody SetmealDto setmealDto){

        return setmealService.saveSetmeal(setmealDto);
    }

    /**
     * 查询套餐列表
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public ResponseResult<Page> getSetmealList(Integer page,Integer pageSize,String name){

        return setmealService.querySetmealList(page,pageSize,name);
    }

    /**
     * 删除对应id的套餐以及关联的菜品数据
     * @param ids
     * @return
     */
    @DeleteMapping
    public ResponseResult<String> deleteSetmeals(@RequestParam("ids") List<Long> ids){

        return setmealService.removeSetmeals(ids);
    }

    /**
     * 修改套餐状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public ResponseResult<String> updateStatus
            (@PathVariable Integer status,@RequestParam List<Long> ids){

        return setmealService.updateStatus(status,ids);
    }
}
