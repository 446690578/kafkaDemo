package com.ben.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisUtil  {

    @Autowired
    private StringRedisTemplate template;



//    @Autowired
//    public RedisDemo() {
//        this.redisTemplate = this.template;
//    }

    public  void set(String key,String val){
        template.opsForValue().set(key,val);
    }

    public  String get(String key){
        return template.opsForValue().get(key);
    }



}
