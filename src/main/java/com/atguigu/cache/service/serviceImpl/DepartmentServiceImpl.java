package com.atguigu.cache.service.serviceImpl;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.mapper.DepartmentMapper;
import com.atguigu.cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author shkstart
 * @create 2018-12-04 10:32
 */
@CacheConfig(cacheManager = "departCacheManager")
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    public DepartmentMapper departmentMapper;

   /* @Qualifier("departCacheManager")
    @Autowired
    public RedisCacheManager deptCacheManager;*/

    //注解的方式使用缓存
    @Override
    @Cacheable(cacheNames={"department"})
    public Department getEmpById(Integer id) {
        System.out.println("查询部门:"+id);
        Department department =departmentMapper.getEmpById(id);
        return department;
    }

    //编码的方式:使用缓存管理器得到缓存，进行api调用;
  /*  public Department getEmpById(Integer id) {
        System.out.println("查询部门:"+id);
        Department department =departmentMapper.getEmpById(id);

        //获取某个缓存
        Cache deptCache = deptCacheManager.getCache("dept");
        deptCache.put("dept:1",department);
        return department;
    }*/
}
