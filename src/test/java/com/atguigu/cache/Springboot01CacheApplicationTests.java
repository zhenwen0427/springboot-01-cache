package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

    @Autowired
    public EmployeeMapper employeeMapper;

    @Autowired
    public StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串

   /* @Autowired
    public RedisTemplate redisTemplate;*/   //k-v都是对象的

    @Autowired
    public RedisTemplate<Object,Employee> empRedisTemplate;

    /**
     * Redis常见的五大数据类型:
     *      String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     *      stringRedisTemplate.opsForValue[String(字符串) ]
     *      stringRedisTemplate.opsForList()[List(列表) ]
     *      stringRedisTemplate.opsForSet()[Set(集合) ]
     *      stringRedisTemplate.opsForHash[Hash(散列) ]
     *      stringRedisTemplate.opsForZSet[ZSet(有序集合) ]
     */
    @Test
    public void test01(){
        //给redis中保存数据
       /* stringRedisTemplate.opsForValue().append("msg","hello");
        String msg =stringRedisTemplate.opsForValue().get("msg");
        System.out.println("msg:"+msg);*/

     /*   stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");
        stringRedisTemplate.opsForList().leftPush("mylist","3");*/

    }

    /**
     *  测试保存对象
     */
    @Test
    public void test02(){
        //Employee employee = employeeMapper.getEmpById(2);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        //redisTemplate.opsForValue().set("emp",employee);
        //1、将数据以json的方式保存
            //1.1:自己将对象转为json
            //1.2:redisTemplate默认的序列规则;改变默认的序列化规则;
            //redisTemplate.opsForValue().set("emp",employee);
        //empRedisTemplate.opsForValue().set("emp2",employee);
    }

  /*  @Test
    public void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.print("employee:"+employee);
    }*/

}
