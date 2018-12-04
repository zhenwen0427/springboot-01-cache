package com.atguigu.cache.controller;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author shkstart
 * @create 2018-11-20 16:25
 */
@Controller
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @ResponseBody
    @GetMapping("/emp/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmpById(id);
        return employee;
    }

    @ResponseBody
    @GetMapping("/emp")
    public Employee updateEmployee(Employee employee){
        employee = employeeService.updateEmployee(employee);
        return employee;
    }

    @ResponseBody
    @GetMapping("/del/{id}")
    public String deleteEmpById(@PathVariable("id") Integer id){
        employeeService.deleteEmpById(id);
        return "Success";
    }
}
