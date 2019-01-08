package com.ben.test;

import com.ben.machine.Ben;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@SpringBootTest
public class TestFunction {

    @Test
    public void testIp(){
        try {

            System.out.println("ip地址：" + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    /*
    * @Author: Ben.Yuan
    * @Description:     lambda表达式
    *
    *    1.    () -> { }   ->左边是参数，右边是代码处理块
    *    2.    -> 和 :: 同理
    *
    * @Date: 18:24 AM 2018/12/6
    */
    @Test
    public void testJDK(){

        String[] arrs = {"my","world","welcome","meet","you","aaa"};
        List<String> list = Arrays.asList(arrs);
        List<String> news = new ArrayList<>();


        list.stream().forEach(x -> System.out.println(x));  //拿到每个元素打印
        list.stream().forEach(x -> {if (x == "world") { news.add(x);}}); //拿到元素进行处理
        news.stream().forEach(System.out::println);         //如果只进行打印，可以使用方法的引用。



       Ben ben1 = new Ben("yuan33",1);
       Ben ben2 = new Ben("ming1",2);

        List<Ben> l2 = new ArrayList<>();
        l2.add(ben1);
        l2.add(ben2);
        Ben a = l2.stream().max(new Comparator<Ben>() {
            @Override
            public int compare(Ben o1, Ben o2) {
                Integer b = o1.getName().length();
                return b.compareTo(o2.getName().length());
            }
        }).get();
        System.out.println("ben a : " + a.getName());



    }




}
