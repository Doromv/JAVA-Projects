package com.doromv.store.service.impl;

import com.doromv.store.entity.District;
import com.doromv.store.mapper.DistrictMapper;
import com.doromv.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-04-10:46
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list ;
    }

    @Override
    public String getNameBycode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
