package com.ben.consume;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
* @Author: Ben.Yuan
* @Description:自定义消费，消费完指定offset提交
* @Date: 10:14 AM 2018/12/3
*/
public class CustomConsumer {

    private static final String broker = "127.0.0.1:9092";
    private static final String topic = "an1130";

    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.put("bootstrap.servers", broker);
        prop.put("group.id", "group14");
//        prop.put("enable.auto.commit", "true");
        prop.put("session.timeout.ms", "30000");
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        prop.put("auto.offset.reset", "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        consumer.subscribe(Collections.singletonList(topic));
        try {
            int i = 0;
            Map<TopicPartition,OffsetAndMetadata> currentOffsets = new HashMap<>();
            while (true) {
                int count = 0;  // 消息数量提交
                System.out.println("\n\n\n第" + ++i + "次尝试\n\n\n");
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.printf("消费offset = %d, value = %s \n", consumerRecord.offset(), consumerRecord.value());

                    if (++count % 500 == 0){
                        currentOffsets.put(new TopicPartition(consumerRecord.topic(),consumerRecord.partition())
                                ,new OffsetAndMetadata(consumerRecord.offset()-500,"no metadata"));
                        System.out.println("\n#####\n" +  (int)(consumerRecord.offset()-500) + "\n#####\n");
                    }

                }

                consumer.commitSync(currentOffsets);  //同步提交 poll()获取的最新消息偏移量,同时会阻塞程序，降低吞吐量
//                consumer.commitAsync(currentOffsets,null);   //异步提交 遇到错误这次提交就终结了，不会提交第二次
                Thread.sleep(10000);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            consumer.close();//关闭之前方法里会自动提交最新的消息偏移量
        }
    }

}







