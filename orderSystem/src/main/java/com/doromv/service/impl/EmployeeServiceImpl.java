package com.doromv.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.mapper.EmployeeMapper;
import com.doromv.pojo.Employee;
import com.doromv.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
* @author DoromvQAQ
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2022-05-22 23:12:06
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService {

}




