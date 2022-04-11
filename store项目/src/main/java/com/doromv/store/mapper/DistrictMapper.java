package com.doromv.store.mapper;

import com.doromv.store.entity.District;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-04-10:31
 */
public interface DistrictMapper {
    List<District> findByParent(String parent);
    String findNameByCode(String code);
}
