package com.ben.produce;


import com.ben.KafkaApplication;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Properties;

public class ProduceSecurity {

    private static final String Topic = "streams-in";
    private static final Logger logger = LoggerFactory.getLogger(KafkaApplication.class);
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers","127.0.0.1:9092");
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("buffer.memory", "33554432");
        properties.put("compression.type","gzip");

        //控制台会打印生产者的信息
        Producer<String,String> producer = new KafkaProducer<String,String>(properties);
        LocalDateTime localDateTime = LocalDateTime.now();
        String curTime = localDateTime.toString();
        try {

        for (int i = 0; i <3000 ; i++) {
            String msg = curTime  + " "+Topic + " " + i  ;
//
            ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>(Topic,msg);
            System.out.println("分区：" + producerRecord.partition());
            System.out.println("key：" + producerRecord.key());
            System.out.println("value：" + producerRecord.value());
            System.out.println("主题：" + producerRecord.topic());
            System.out.println("生产：" + msg);
//            异步发送消息，失败会记录
            producer.send(producerRecord,new ProducerCallBack());
//            producer.send(producerRecord);

        }


            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("error msg :" + e.getMessage());
        }

        producer.close();


        System.out.println("max Integer：" + Integer.MAX_VALUE );


    }






}
