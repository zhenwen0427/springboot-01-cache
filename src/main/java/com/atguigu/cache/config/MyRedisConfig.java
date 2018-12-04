package com.atguigu.cache.config;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * redis缓存配置
 *
 */
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    //CacheManagerCustomizers 可以来定制缓存的一些规则
    @Primary    //将某个缓存管理器作为默认的
    @Bean
    public RedisCacheManager empCacheManager(RedisTemplate<Object,Employee> empRedisTemplate){
        RedisCacheManager redisCacheManager = new RedisCacheManager(empRedisTemplate);
        // key 多了一个前缀

        // 使用前缀，默认会将CacheName作为key的前缀
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }

    @Bean
    public RedisTemplate<Object, Department> departmentRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    public RedisCacheManager departCacheManager(RedisTemplate<Object,Department> departmentRedisTemplate){
        RedisCacheManager redisCacheManager = new RedisCacheManager(departmentRedisTemplate);
        // key 多了一个前缀

        // 使用前缀，默认会将CacheName作为key的前缀
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }

}
