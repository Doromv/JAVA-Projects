package com.doromv.store.controller;

import com.doromv.store.entity.District;
import com.doromv.store.service.IDistrictService;
import com.doromv.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-04-10:59
 */
@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService districtService;
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
