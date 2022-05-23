package com.doromv.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doromv.mapper.EmployeeMapper;
import com.doromv.pojo.Employee;
import com.doromv.service.EmployeeService;
import com.doromv.service.ex.EmployeeException;
import com.doromv.utils.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
/**
* @author DoromvQAQ
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2022-05-22 23:12:06
 * Employee的Service层
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService {

    /**
     * 登录
     * @param employee 前端传来的用户信息
     * @return
     */
    @Override
    public ResponseResult<Employee> login(Employee employee) {
        //1.将页面提交的密码用MD5加密
        String pwdMD5 = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        //2.根据页面提交的用户名查询对应的用户
        Employee emp = query().eq("username", employee.getUsername()).one();
        //2.1如果查询为空则返回error
        if(ObjectUtils.isEmpty(emp)){
           throw new EmployeeException("用户名错误！");
        }
        //2.2如果查询成功则进行密码对比
        //2.2.1密码错误返回error
        if(!pwdMD5.equals(emp.getPassword())){
            throw new EmployeeException("密码错误！");
        }
        //2.2.2密码正确则查询员工状态
        //2.2.2.1员工状态为已禁用则返回error
        if(emp.getStatus()==0){
            throw new EmployeeException("账户已禁用！");
        }
        //2.2.2.2员工状态正常，返回登录成功
        return ResponseResult.success(emp);
    }

    /**
     * 添加用户
     * @param createrId 当前用户的ID
     * @param employee 要添加的用户的信息
     * @return
     */
    @Override
    public ResponseResult<String> addEmployee(Long createrId, Employee employee) {
        //1.检查创建者是否为管理
        Employee admin = query().eq("username", "admin").one();
        Long id = admin.getId();
        if(id!=createrId){
            throw new EmployeeException("权限不足！");
        }
        //2.查询数据库是否已经注册过该账号
         Integer count = query().eq("username", employee.getUsername()).count();
        if(count!=0){
            throw new EmployeeException("账号已被注册！");
        }
        //3.查询身份证是否已被使用
        count = query().eq("id_number", employee.getIdNumber()).count();
        if(count!=0){
            throw new EmployeeException("身份证已被使用！");
        }
        //4.查询电话号码是否已被注册
        count = query().eq("phone", employee.getPhone()).count();
        if(count!=0){
            throw new EmployeeException("电话号码已被注册！");
        }
        //5.设置初始密码,MD5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //6.设置创建者id
        employee.setCreateUser(createrId);
        //7.设置更新者id
        employee.setUpdateUser(createrId);
        //8.设置创建时间
        employee.setCreateTime(LocalDateTime.now());
        //9.设置更新时间
        employee.setUpdateTime(LocalDateTime.now());
        //10.将用户保存到数据库
        boolean row = save(employee);
        if(row==false){
            throw new RuntimeException("保存失败！");
        }
        return ResponseResult.success("新增员工成功");
    }

    /**
     * 得到用户列表
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public ResponseResult<Page<Employee>> getEmployeeList
    (Integer page, Integer pageSize, String name) {
        //1.构造分页构造器
        Page<Employee> pageInfo = new Page<>(page, pageSize);
        //2.构造条件构造器
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        //3.添加条件
        wrapper.like(!ObjectUtils.isEmpty(name), Employee::getName, name)
                .orderByDesc(Employee::getUpdateTime);
        //4.执行查询
        page(pageInfo,wrapper);

        return ResponseResult.success(pageInfo);
    }

    /**
     * 修改用户的状态
     * @param modifyerId
     * @param employee
     * @return
     */
    @Override
    public void modifyEmployeeInfo(Long modifyerId, Employee employee) {
        //1.更新时间、更新者id
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(modifyerId);
        boolean row = updateById(employee);
        //2.判断是否更新成功
        if(row==false){
            throw new EmployeeException("发生未知异常，更新状态失败！");
        }
        return;
    }

    /**
     * 根据id查询员工信息
     * @param employeeId
     * @return
     */
    @Override
    public Employee queryEmployeeById(Long employeeId) {
        Employee emp = getById(employeeId);
        if(ObjectUtils.isEmpty(emp)){
            throw new EmployeeException("无该用户信息！");
        }
        return emp;
    }

}




