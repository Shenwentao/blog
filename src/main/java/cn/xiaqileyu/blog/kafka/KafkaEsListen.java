package cn.xiaqileyu.blog.kafka;

import cn.xiaqileyu.blog.domain.entity.ElasticEntity;
import cn.xiaqileyu.blog.domain.entity.KafkaEntity;
import cn.xiaqileyu.blog.util.ElasticSearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * kafka同步es监听类
 *
 * @author swt
 * @date 2020/8/31
 */
@Component
@Slf4j
public class KafkaEsListen {

    @Autowired
    private ElasticSearchUtil elasticSearchUtil;

    /**
     * 监听处理方法
     *
     * @param consumerRecord 接受信息
     */
    @KafkaListener(topics = "blog_es")
    private void process(ConsumerRecord<?, ?> consumerRecord) {
        log.info("----接受kafka消息----");
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        //判断是否包含值
        if (!kafkaMessage.isPresent()) {
            log.warn("无数据消费，topic: {}", consumerRecord.topic());
            return;
        }
        //得到Optional实例中的值
        KafkaEntity kafkaEntity = (KafkaEntity) kafkaMessage.get();
        switch (kafkaEntity.getType()) {
            case "post":
                //执行es插入｜修改逻辑
                ElasticEntity<Object> elasticEntity = new ElasticEntity<>(kafkaEntity.getId(), kafkaEntity.getData());
                elasticSearchUtil.insertOrUpdateOne(ElasticSearchUtil.INDEX_NAME, elasticEntity);
                break;
            case "delete":
                //执行es删除逻辑
                elasticSearchUtil.deleteByQuery(ElasticSearchUtil.INDEX_NAME, new TermQueryBuilder("id", kafkaEntity.getId()));
                break;
            default:
                log.info("无执行逻辑");
        }
    }
}
