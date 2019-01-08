package com.ben.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestForRedis {

    @Autowired
   private StringRedisTemplate redisTemplate;

    @GetMapping("/TestForRedis")
    public Object get(){

        redisTemplate.opsForValue().set("aaa","yuan");
        String a  = redisTemplate.opsForValue().get("aaa");
        System.out.println("aaa:" + a);
        return a;
    }



    @Autowired
    private RedisUtil redisDemo ;

    @GetMapping("/aaa")
    public Object get1(){

        redisTemplate.opsForValue().set("aaa","yuan");
        String a  = redisTemplate.opsForValue().get("aaa");
        System.out.println("aaa:" + a);

        redisDemo.set("bbb","ying");
        String b = redisDemo.get("bbb");
        return a +" | " + b;
    }
    @GetMapping("/bbb")
    public Object get2(){

        String a  = redisTemplate.opsForValue().get("aaa");
        System.out.println("aaa:" + a);

        String b = redisDemo.get("bbb");
        return a +" | " + b;
    }



}
