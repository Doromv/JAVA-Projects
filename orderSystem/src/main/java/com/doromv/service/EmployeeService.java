package com.doromv.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.doromv.pojo.Employee;
import com.doromv.utils.ResponseResult;

public interface EmployeeService extends IService<Employee> {

    ResponseResult<Employee> login(Employee employee);

    ResponseResult<String> addEmployee(Long createrId,Employee employee);

    ResponseResult<Page<Employee>> getEmployeeList(Integer page, Integer pageSize, String name);

    void modifyEmployeeInfo(Long modifyerId, Employee employee);

    Employee queryEmployeeById(Long employeeId);
}
