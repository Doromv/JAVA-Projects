package com.doromv.mapper;

import com.doromv.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DoromvQAQ
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-05-24 08:50:56
* @Entity doromv.pojo.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




