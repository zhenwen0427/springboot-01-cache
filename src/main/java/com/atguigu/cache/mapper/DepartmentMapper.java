package com.atguigu.cache.mapper;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author shkstart
 * @create 2018-11-20 16:32
 */
@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id = #{id}")
    public Department getEmpById(Integer id);
}
