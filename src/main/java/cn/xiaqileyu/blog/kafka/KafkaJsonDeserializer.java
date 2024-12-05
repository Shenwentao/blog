package cn.xiaqileyu.blog.kafka;

import cn.xiaqileyu.blog.domain.entity.KafkaEntity;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * kafka反序列化
 *
 * @author swt
 * @date 2020/8/31
 */
public class KafkaJsonDeserializer implements Deserializer<KafkaEntity> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public KafkaEntity deserialize(String topic, byte[] data) {
        return JSON.parseObject(data, KafkaEntity.class);
    }

    @Override
    public void close() {

    }
}