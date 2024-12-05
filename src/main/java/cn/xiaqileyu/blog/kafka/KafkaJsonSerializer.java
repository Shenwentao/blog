package cn.xiaqileyu.blog.kafka;

import cn.xiaqileyu.blog.domain.entity.KafkaEntity;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * kafka序列化
 *
 * @author swt
 * @date 2020/8/31
 */
public class KafkaJsonSerializer implements Serializer<KafkaEntity> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, KafkaEntity data) {
        return JSON.toJSONBytes(data);
    }

    @Override
    public void close() {

    }
}