package com.doromv.store.service;

import com.doromv.store.entity.District;
import com.doromv.store.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-04-10:44
 */
public interface IDistrictService {
    List<District> getByParent(String parent);
    String getNameBycode(String code);
}
