package com.atguigu.cache.service;

import com.atguigu.cache.bean.Employee;

/**
 * @author shkstart
 * @create 2018-11-20 17:09
 */
public interface EmployeeService {

    public Employee getEmpById(Integer id);

    public Employee updateEmployee(Employee employee);

    public void deleteEmpById(Integer id);

}
