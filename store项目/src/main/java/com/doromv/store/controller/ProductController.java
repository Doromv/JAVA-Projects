package com.doromv.store.controller;

import com.doromv.store.entity.Product;
import com.doromv.store.service.IProductService;
import com.doromv.store.service.impl.ProductServiceImpl;
import com.doromv.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-11-8:07
 */
@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
    @Autowired
    IProductService productService;
    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> data = productService.findHostList();
        return new JsonResult<>(OK,data);
    }
    @GetMapping("details/{id}")
    public JsonResult<Product> getById(@PathVariable("id") Integer id){
        Product data = productService.getById(id);
        return new JsonResult<Product>(OK,data);
    }
}
