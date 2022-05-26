package com.doromv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.dto.DishDto;
import com.doromv.pojo.Dish;
import com.doromv.pojo.DishFlavor;
import com.doromv.service.CategoryService;
import com.doromv.service.DishFlavorService;
import com.doromv.service.DishService;
import com.doromv.mapper.DishMapper;
import com.doromv.service.ex.DishException;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author DoromvQAQ
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-05-24 10:47:29
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

    @Autowired
    DishFlavorService dishFlavorService;

    @Autowired
    CategoryService categoryService;

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult<String> saveDish(DishDto dishDto) {
        //判断口味信息是否填写
        if(ObjectUtils.isEmpty(dishDto.getFlavors())){
            throw new DishException("请填写口味信息！");
        }
        //1.保存菜品基本信息到菜品表中
        boolean row = save(dishDto);
        if(row==false){
            throw new DishException("保存基本信息时发生未知异常，请重试！");
        }
        //2.保存菜品口味数据到菜品口味表
        Long dishID = dishDto.getId();
        List<DishFlavor> dishFlavors = dishDto.getFlavors();
        List<DishFlavor> dishFlavorList = dishFlavors.stream()
                .map(i -> {
                    i.setDishId(dishID);
                    return i;
                })
                .collect(Collectors.toList());
         row = dishFlavorService.saveBatch(dishFlavorList);
        if(row==false){
            throw new DishException("保存口味信息时发生未知异常，请重试！");
        }
        return ResponseResult.success("添加成功");
    }

    /**
     * 查询并改造Dish列表
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public ResponseResult<Page> getDishList
            (Integer page, Integer pageSize, String name) {
        //1.创建分页构造器对象
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        //2.创建条件构造器对象
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        //3.添加过滤条件
        wrapper.like(!ObjectUtils.isEmpty(name), Dish::getName, name)
                .orderByAsc(Dish::getUpdateTime);
        //4.执行分页查询
        page(pageInfo,wrapper);
        //5.创建关于DishDto的分页构造器
        Page<DishDto> pageInfo2 = new Page<>();
        //6.拷贝除了Dish列表的其他属性
        BeanUtils.copyProperties(pageInfo,pageInfo2, "records");
        //7.改造Dish列表
        List<Dish> dishList = pageInfo.getRecords();
        List<DishDto> dishDtoList = dishList.stream()
                .map((i) -> {
                    DishDto dishDto = new DishDto();
                    BeanUtils.copyProperties(i, dishDto);
                    Long categoryId = i.getCategoryId();
                    String categoryName = categoryService.getById(categoryId).getName();
                    dishDto.setCategoryName(categoryName);
                    return dishDto;
                })
                .collect(Collectors.toList());
        //8.将改造成DishDto的列表传入pageInfo2
        pageInfo2.setRecords(dishDtoList);
        return ResponseResult.success(pageInfo2);
    }

    /**
     * 根据id查询菜品信息和口味信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult<DishDto> queryDishById(Long id) {
        //1.查询菜品基本信息
        if(ObjectUtils.isEmpty(id)){
            throw new DishException("商品id不能为空!");
        }
        Dish dishInfo = getById(id);
        //2.查询口味信息
        List<DishFlavor> dishFlavorList = dishFlavorService.query().eq("dish_id", id).list();
        //3.拷贝数据
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dishInfo,dishDto);
        //4.将口味信息保存到dishDto
        dishDto.setFlavors(dishFlavorList);
        //5.返回结果
        return ResponseResult.success(dishDto);
    }

    /**
     * 更新菜品信息和口味信息
     * @param dishDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult<String> updateDishInfo(DishDto dishDto) {
        //1.更新菜品信息
        boolean row = updateById(dishDto);
        if(row==false){
            throw new DishException("菜品更新发生位置的异常，请重试！");
        }
        //2.清理当前菜品的口味信息
        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper();
        wrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(wrapper);
        //3.插入新的菜品口味信息
        List<DishFlavor> dishFavorList = dishDto.getFlavors();
        List<DishFlavor> dishFlavorList = dishFavorList.stream()
                .map((i) -> {
                    i.setDishId(dishDto.getId());
                    return i;
                })
                .collect(Collectors.toList());
        row = dishFlavorService.saveBatch(dishFlavorList);
        if(row==false){
            throw new DishException("菜品更新发生位置的异常，请重试！");
        }
        return ResponseResult.success("修改菜品信息成功");
    }

    /**
     * 修改菜品状态
     * @param status
     * @param ids
     * @return
     */
    @Override
    public ResponseResult<String> updateDishStatus(Integer status, ArrayList<Long> ids) {
        //根据id更改菜品状态
        List<Dish> dishList = ids.stream()
                .map(i -> {
                    Dish dish = new Dish();
                    dish.setId(i);
                    dish.setStatus(status);
                    return dish;
                })
                .collect(Collectors.toList());
        boolean row = updateBatchById(dishList);
        if(row==false){
            throw new DishException("更新状态时发生位置异常，请重试！");
        }
        return ResponseResult.success("状态修改成功");
    }

    /**
     * 根据分类id查询菜品
     * @param dish
     * @return
     */
    @Override
    public ResponseResult<List<DishDto>> queryDishListByCategoryId(Dish dish) {
        //根据分类id查询对应的菜品
        List<Dish> dishList = query()
                .eq("status",1)
                .eq(!ObjectUtils.isEmpty(dish.getCategoryId()),"category_id", dish.getCategoryId())
                .orderByAsc(String.valueOf(dish.getSort()))
                .orderByAsc(String.valueOf(dish.getUpdateTime()))
                .like(!ObjectUtils.isEmpty(dish.getName()),"name",dish.getName()).list();
        List<DishDto> dishDtoList = dishList.stream()
                .map(i -> {
                    DishDto dishDto = new DishDto();
                    BeanUtils.copyProperties(i, dishDto);
                    List<DishFlavor> dishFlavorList = dishFlavorService
                            .query().eq("dish_id", i.getId()).list();
                    dishDto.setFlavors(dishFlavorList);
                    return dishDto;
                })
                .collect(Collectors.toList());
        return ResponseResult.success(dishDtoList);
    }

}




