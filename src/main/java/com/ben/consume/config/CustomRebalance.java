package com.ben.consume.config;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

/*
* @Author: Ben.Yuan
* @Description:监听器 消费者触发再均衡
*
*
*     onPartitionsRevoked 再均衡开始之前和消费者停止读取消息之后被调用
*     onPartitionsAssigned 重新分配分区之后和消费者开始读取消息之前被调用
*
*      consumer.subscribe(topic,new CustomRebalance())
* @Date: 5:01 PM 2018/12/3
*/
public class CustomRebalance implements ConsumerRebalanceListener {

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {

        System.out.println("停止读取消息之后，再均衡之前");


    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {

        System.out.println("重新分区之后，读取消息之前");

    }
}
