package com.ben.consume;

import com.ben.consume.config.CustomRebalance;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/*
* @Author: Ben.Yuan
* @Description:消费者
* @Date: 5:14 PM 2018/12/3
*/
public class GroupConsumer {

    private static final String broker = "127.0.0.1:9092";
    private static final String topic = "an1130";

    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.put("bootstrap.servers", broker);
        prop.put("group.id", "group1203");
        prop.put("enable.auto.commit", "true");
        prop.put("session.timeout.ms", "30000");
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        prop.put("auto.offset.reset", "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        consumer.subscribe(Collections.singletonList(topic),new CustomRebalance());
        int i = 0;
        try {

            while (true) {
                System.out.println("\n\n\n第" + ++i + "次尝试\n\n\n");
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.printf("消费offset = %d, value = %s \n", consumerRecord.offset(), consumerRecord.value());
                }
//                consumer.commitSync();  //同步提交 poll()获取的最新消息偏移量,同时会阻塞程序，降低吞吐量
//                consumer.commitAsync();   //异步提交 遇到错误这次提交就终结了，不会提交第二次
                Thread.sleep(10000);
                if (i == 20) {
                    consumer.close();//关闭之前方法里会自动提交最新的消息偏移量
                    break;
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            consumer.close();
        }
    }

}







