package com.doromv.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doromv.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DoromvQAQ
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2022-05-22 23:12:06
* @Entity .pojo.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}




