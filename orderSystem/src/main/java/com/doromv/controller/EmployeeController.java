package com.doromv.controller;

import com.doromv.pojo.Employee;
import com.doromv.service.EmployeeService;
import com.doromv.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Doromv
 * @create 2022-05-22-23:19
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseResult<Employee> login
            (HttpServletRequest request, @RequestBody Employee employee){
        //1.将页面提交的密码用MD5加密
        String pwdMD5 = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        //2.根据页面提交的用户名查询对应的用户
        Employee emp = employeeService.query().eq("username", employee.getName()).one();
        //2.1如果查询为空则返回error
        if(ObjectUtils.isEmpty(emp)){
            return ResponseResult.error("用户名错误！");
        }
        //2.2如果查询成功则进行密码对比
        //2.2.1密码错误返回error
        if(!pwdMD5.equals(emp.getPassword())){
            return ResponseResult.error("密码错误！");
        }
        //2.2.2密码正确则查询员工状态
        //2.2.2.1员工状态为已禁用则返回error
        if(emp.getStatus()==0){
            return ResponseResult.error("账户已禁用！");
        }
        //2.2.2.2登录成功返回seccess,并且将账户id写入session
        request.getSession().setAttribute("employee",emp.getId());
        return ResponseResult.success(emp);
    }
}
