package com.doromv.dto;

import com.doromv.pojo.Setmeal;
import com.doromv.pojo.SetmealDish;
import lombok.Data;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-05-25-8:40
 */
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
