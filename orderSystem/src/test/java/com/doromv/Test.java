package com.doromv;

import com.doromv.pojo.Employee;
import com.doromv.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @author Doromv
 * @create 2022-05-23-13:04
 */
@SpringBootTest
public class Test {

    @Autowired
    EmployeeService employeeService;

    @org.junit.jupiter.api.Test
    public void test(){
        Integer count = employeeService.query().eq("username", "d").count();
        System.out.println(count);
    }
}
