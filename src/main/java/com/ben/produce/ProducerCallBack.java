package com.ben.produce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerCallBack implements Callback {

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null){
            System.out.println("元数据： " + e.getMessage());
            System.out.println("元数据： " + recordMetadata.toString());
        }
//        recordMetadata.offset();
    }
}
