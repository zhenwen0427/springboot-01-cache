package com.atguigu.cache.controller;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shkstart
 * @create 2018-12-04 10:41
 */

@Controller
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;

    @ResponseBody
    @GetMapping("/department/{id}")
    public Department getDepartmentId(@PathVariable("id") Integer id){
        Department department = departmentService.getEmpById(id);
        return department;
    }

}
