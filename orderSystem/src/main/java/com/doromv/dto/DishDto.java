package com.doromv.dto;

import com.doromv.pojo.Dish;
import com.doromv.pojo.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-05-24-17:56
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
