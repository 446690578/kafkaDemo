package com.ben;

import com.alibaba.fastjson.JSONObject;
import com.ben.consume.CustomConsumer;
import com.ben.produce.ProducerCallBack;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@RestController
  //8763
public class KafkaApplication {

    private static final String Topic = "an1130";
    private static final Logger logger = LoggerFactory.getLogger(KafkaApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class,args);
        System.out.println("that is ok");
    }

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name",required = false,defaultValue = "yuan")
                                   String name) {


        logger.error("error error error error error error");
        logger.debug("debug debug debug debug debug debug");
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
        for (int i = 0; i <1000 ; i++) {
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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producer.close();

        return "hi "+name;
    }




}
