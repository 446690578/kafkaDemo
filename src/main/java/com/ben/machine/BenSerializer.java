package com.ben.machine;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class BenSerializer implements Serializer<Ben> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {
        //配置
    }

    @Override
    public byte[] serialize(String s, Ben ben) {

        try {
            byte[] name;
            int stringSize;

            if (ben != null) {
                if (ben.getName() != null) {
                    name = ben.getName().getBytes("UTF-8");
                    stringSize = name.length;
                }else {
                    name = new byte[0];
                    stringSize = 0;
                }
                ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
                buffer.putInt(ben.getId());
                buffer.putInt(stringSize);
                buffer.put(name);           //String类型要放入length和数据
                return buffer.array();
            } else {
                return new byte[0];
            }
        }catch (Exception e){
            throw new RuntimeException(" 序列时发生错误");
        }
    }

    @Override
    public void close() {
        //关闭
    }
}
