package cn.xiaqileyu.blog.util;

import cn.xiaqileyu.blog.domain.entity.KafkaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author swt
 * @date 2020/6/24
 */
@Slf4j
@Component
public class KafkaProducerUtil {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * de
     * 发送一条kafka信息
     *
     * @param topic   topic
     * @param message 消息体
     */
    public void sendMessage(String topic, KafkaEntity message) {
        kafkaTemplate.send(topic, message).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("生产者-发送消息失败：topic: {} , message: {}", topic, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("生产者-发送消息成功：topic: {} , message: {}", topic, result.toString());
            }
        });
    }
}
