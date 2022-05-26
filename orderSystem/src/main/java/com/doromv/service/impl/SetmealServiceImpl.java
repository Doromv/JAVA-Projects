package com.doromv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.dto.SetmealDto;
import com.doromv.pojo.Category;
import com.doromv.pojo.Setmeal;
import com.doromv.pojo.SetmealDish;
import com.doromv.service.CategoryService;
import com.doromv.service.SetmealDishService;
import com.doromv.service.SetmealService;
import com.doromv.mapper.SetmealMapper;
import com.doromv.service.ex.CategoryException;
import com.doromv.service.ex.DishException;
import com.doromv.service.ex.SetmealException;
import com.doromv.utils.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author DoromvQAQ
* @description 针对表【setmeal(套餐)】的数据库操作Service实现
* @createDate 2022-05-24 10:47:29
*/
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements SetmealService{

    @Autowired
    SetmealDishService setmealDishService;

    @Autowired
    CategoryService categoryService;

    /**
     * 保存套餐信息
     * @param setmealDto
     * @return
     */
    @Override
    @Transactional
    public ResponseResult<String> saveSetmeal(SetmealDto setmealDto) {

        //1.保存套餐的基本信息
        boolean row = save(setmealDto);
        if(row==false){
            throw new SetmealException("保存套餐信息时发生未知异常，请重试！");
        }
        //2.保存套餐所关联的菜品信息
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        List<SetmealDish> setmealDishList = setmealDishes.stream()
                .map(i -> {
                    i.setSetmealId(setmealDto.getId());
                    return i;
                })
                .collect(Collectors.toList());
        row = setmealDishService.saveBatch(setmealDishList);
        if(row==false){
            throw new SetmealException("保存套餐信息时发生未知异常，请重试！");
        }
        return ResponseResult.success("保存套餐信息成功");
    }

    /**
     * 查询套餐列表
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public ResponseResult<Page> querySetmealList(Integer page, Integer pageSize, String name) {
        //1.创建分页构造器
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        //2.创建条件对象
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        //3.添加条件
        wrapper.like(!ObjectUtils.isEmpty(name),Setmeal::getName,name)
                .orderByAsc(Setmeal::getUpdateTime);
        //4.查询分页
        page(pageInfo, wrapper);
        //5.新建关于SetmealDto的分页构造器
        Page<SetmealDto> pageInfo2 = new Page<>(page,pageSize);
        //6.将pageinfo拷贝到pageinfo2,records属性除外
        BeanUtils.copyProperties(pageInfo,pageInfo2,"records");
        //7.对records进行改造并封装到pageinfo2中
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> setmealDtoList = records.stream()
                .map(i -> {
                    SetmealDto setmealDto = new SetmealDto();
                    BeanUtils.copyProperties(i, setmealDto);
                    Category category = categoryService.query().eq("id", i.getCategoryId()).one();
                    if(ObjectUtils.isEmpty(category)){
                        throw new CategoryException("不存在此分类！");
                    }
                    setmealDto.setCategoryName(category.getName());
                    return setmealDto;
                }).collect(Collectors.toList());
        //8.将改造完的records封装到pageinfo2
        pageInfo2.setRecords(setmealDtoList);
        //9.返回结果
        return ResponseResult.success(pageInfo2);
    }

    /**
     * 删除对应id的套餐以及关联的菜品数据
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public ResponseResult<String> removeSetmeals(List<Long> ids) {
        //1.查询套餐状态，确认其是否可以删除
        Integer count = query().in("id", ids).eq("status", 1).count();
        if(count>0){
            throw new SetmealException("套餐还在售卖中，请先停售再删除！");
        }
        //2.删除套餐
        removeByIds(ids);
        //3.删除对应的关联菜品
        List<SetmealDish> setmealDishList = setmealDishService.query()
                .in("setmeal_id", ids).list();
        List<Long> dishIds = setmealDishList.stream()
                .map(i -> i.getId())
                .collect(Collectors.toList());
        setmealDishService.removeByIds(dishIds);
        return ResponseResult.success("删除成功！");
    }

    /**
     * 修改套餐状态
     * @param status
     * @param ids
     * @return
     */
    public ResponseResult<String> updateStatus(Integer status,List<Long> ids){
        boolean row = update().setSql("status=" + status).in("id", ids).update();
        if(row==false){
            throw new SetmealException("更改状态时发生未知异常，请重试！");
        }
        return ResponseResult.success("更新状态成功");
    }

    /**
     * 根据id查询套餐信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult<SetmealDto> getSetmealById(Long id) {
        if(ObjectUtils.isEmpty(id)){
            throw new DishException("套餐id不能为空!");
        }
        SetmealDto setmealDto = new SetmealDto();
        //查询套餐基本信息
        Setmeal setmealInfo = getById(id);
        //查询菜品信息
        List<SetmealDish> setmealDishesInfo =
                setmealDishService.query().eq("setmeal_id", id).list();
        //查询分类名称
        Long categoryId = setmealInfo.getCategoryId();
        String categoryName = categoryService.getById(categoryId).getName();
        //将数据存入stemalDto中
        BeanUtils.copyProperties(setmealInfo,setmealDto);
        setmealDto.setSetmealDishes(setmealDishesInfo);
        setmealDto.setCategoryName(categoryName);
        return ResponseResult.success(setmealDto);
    }

    /**
     * 更新套餐信息
     * @param setmealDto
     * @return
     */
    @Override
    public ResponseResult<String> updateSetmeal(SetmealDto setmealDto) {
        //更新套餐基本信息
        Setmeal setmealInfo = new Setmeal();
        BeanUtils.copyProperties(setmealDto,setmealInfo);
        boolean row = updateById(setmealInfo);
        if(row==false){
            throw new SetmealException("更新套餐时发生未知异常，请重试！");
        }
        //更新菜品信息
        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishService.remove(wrapper);
        List<SetmealDish> setmealDishesInfo = setmealDto.getSetmealDishes();
        List<SetmealDish> setmealDishList = setmealDishesInfo.stream()
                .map(i -> {
                    i.setSetmealId(setmealDto.getId());
                    return i;
                })
                .collect(Collectors.toList());
        row = setmealDishService.saveBatch(setmealDishList);
        if(row==false){
            throw new SetmealException("更新套餐时发生未知异常，请重试！");
        }
        return ResponseResult.success("更新套餐信息成功");
    }

    /**
     * 根据分类id查询套餐
     * @param setmeal
     * @return
     */
    @Override
    public ResponseResult<List<Setmeal>> querySetmealListByCategoryId(Setmeal setmeal) {
        List<Setmeal> setmealList = query().eq("category_id", setmeal.getCategoryId())
                .eq("status", 1)
                .orderByAsc("update_time").list();
        return ResponseResult.success(setmealList);
    }

}




