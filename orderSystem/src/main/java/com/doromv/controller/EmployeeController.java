package com.doromv.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doromv.pojo.Employee;
import com.doromv.service.EmployeeService;
import com.doromv.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Employee管理
 * @author Doromv
 * @create 2022-05-22-23:19
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 登录
     * @param request
     * @param employee 前端传来的用户信息
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<Employee> login
            (HttpServletRequest request, @RequestBody Employee employee){
       //将账户id写入session并且返回结果;
        ResponseResult<Employee> result = employeeService.login(employee);
        Employee emp = result.getData();
        if(!ObjectUtils.isEmpty(emp)){
            request.getSession().setAttribute("employee",emp.getId());
        }
        return result;
    }


    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ResponseResult<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return ResponseResult.success("退出成功!");
    }

    /**
     * 新增员工
     * @param request
     * @param employee 要添加的用户的信息
     * @return
     */
    @PostMapping
    public ResponseResult<String> addEmployee
    (HttpServletRequest request,@RequestBody Employee employee){

        //获取创建者id
        Long createrId = (Long) request.getSession().getAttribute("employee");

        return employeeService.addEmployee(createrId,employee);
    }

    /**
     * 展示用户列表
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public ResponseResult<Page<Employee>> showEmployeeList
            (Integer page,Integer pageSize,String name){
        return employeeService.getEmployeeList(page, pageSize,name);
    }

    /**
     * 修改员工信息
     * @param request
     * @param employee 前端传来的用户信息
     * @return
     */
    @PutMapping
    public ResponseResult<String> update(HttpServletRequest request,@RequestBody Employee employee){
        Long modifyerId = (Long) request.getSession().getAttribute("employee");
        employeeService.modifyEmployeeInfo(employee);
        return ResponseResult.success("员工信息修改成功！");
    }

    /**
     * 根据id查询用户
     * @param employeeId 用户id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult<Employee> getEmployeeById(@PathVariable("id") Long employeeId){
        Employee employee = employeeService.queryEmployeeById(employeeId);
        return ResponseResult.success(employee);
    }
}
