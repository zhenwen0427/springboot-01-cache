package com.atguigu.cache.service.serviceImpl;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import com.atguigu.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author shkstart
 * @create 2018-11-20 17:14
 */
@CacheConfig(cacheManager = "empCacheManager")
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存; 以后要相同的数据直接从缓存中获取，不用调用方法;
     *
     *  CacheManager管理多个Cache组件的,对缓存的真正CRUD操作在Cache组件中,每一个缓存组件有自己
     *  唯一一个名字
     *
     * 原理:
     *      1、自动配置类:CacheAutoConfiguration
     *      2、缓存的配置类:
     *      3、哪个配置类默认生效：SimpleCacheConfiguration
     *      4、给容器中注册了一个CacheManager: ConcurrentMapCacheManager
     *      5、可能获取和创建ConcurrentMapCache类型的缓存组件;他的作用将数据保存在ConcurrentMap中;
     *
     *      运行流程:
     *      @Cacheable:
     *      1、方法运行之前,先去查询Cache(缓存组件)，按照cacheNames指定的名字获取;
     *              (CacheNanager 先获取相应的缓存), 第一次获取缓存如果没有Cache组件会自动创建。
     *      2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数;
     *          key是按照某种策略生成的;默认是使用keyGenerator生成的,默认使用SimpleKeyGenerator生成key
     *      3、没有查到缓存就调用目标方法;
     *      4、将目标方法返回的结果，放进缓存中
     *
     *      @Cacheable 标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *      如果没有就运行方法并将结果放入缓存;以后再来调用就可以直接使用缓存中的数据;
     *
     *      核心:
     *          1)、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件;
     *          2)、key使用keyGenerator生成的,默认是SimpleKeyGenerator;
     *
     *      几个属性:
     * 	        cacheNames/value：指定缓存组件的名字;
     * 	        key:缓存数据使用的key;可以用它来指定。默认是使用方法参数的值 1-方法的返回值编写SpEL;
     * 	                #id;参数id的值;  #a0   #p0  #root.args[0]
     * 	        keyGenerator:指定缓存管理器;或者cacheResolver指定获取解析器;
     * 	        condition:指定符合条件的情况下缓存;
     * 	        unless:否定缓存;当unless指定的条件为true，方法的返回值就不会被缓存;可以获取到结果
     * 			       进行判断。 unless = "#result == null";
     * 	        sync:是否使用异步模式;
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheNames={"emp"})
    public Employee getEmpById(Integer id) {
        System.out.println("查询员工id为:"+id);
        Employee employee =  employeeMapper.getEmpById(id);
        System.out.println("employee:"+employee);
        return employee;
    }

    /**
     * @CachePut:既调用方法，又更新缓存数据;
     * 修改了数据库的某个数据，同时更新缓存;
     * 运行时机:
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     *  测试步骤:
     *  1、查询1号员工;查到的结果会放在缓存中;
     *          key:1 vaklue:lastName:张三
     *  2、以后查询还是之前的结果
     *  3、更新
     *
     * @param employee
     * @return
     */
    @Override
    @CachePut(cacheNames={"emp"}, key="#employee.id")
    public Employee updateEmployee(Employee employee) {
        System.out.println("updateEmployee:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     *  清除缓存
     * @param id
     */
    @Override
    @CacheEvict(cacheNames={"emp"},key="#id")
    public void deleteEmpById(Integer id) {
        System.out.println("id:"+id);
    }
}
