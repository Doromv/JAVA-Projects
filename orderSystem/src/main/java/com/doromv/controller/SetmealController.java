package com.doromv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.dto.SetmealDto;
import com.doromv.pojo.Dish;
import com.doromv.pojo.Setmeal;
import com.doromv.pojo.SetmealDish;
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

    /**
     * 根据id查询套餐信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult<SetmealDto> getSetmealById(@PathVariable Long id){

        return setmealService.getSetmealById(id);
    }

    /**
     * 更新套餐信息
     * @param setmealDto
     * @return
     */
    @PutMapping
    public ResponseResult<String> updateSetmeal(@RequestBody SetmealDto setmealDto){

        return setmealService.updateSetmeal(setmealDto);
    }

    /**
     * 根据分类id查询套餐
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<List<Setmeal>> getSetmealListByCategoryId(Setmeal setmeal){

        return setmealService.querySetmealListByCategoryId(setmeal);
    }
}
