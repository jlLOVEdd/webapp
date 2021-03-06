package com.wdd.test.comm.util;

import com.google.gson.JsonSyntaxException;
import com.wdd.test.comm.log.InnerLog;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.retry.annotation.EnableRetry;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 13:55 2018/8/22
 */
@EnableRetry
public class ListenerThreadDemo extends Thread {
    public ListenerThreadDemo() {
    }

    @Override
    public void run() {
        InnerLog.info(">>>>>>>>>>Kafka  listener  starting ........");
        System.out.println(">>>>>>>>>>Kafka  listener  starting ........");

        Properties props = new Properties();
        props.put("bootstrap.servers", "106.14.178.69:9092");

        /**
         * latest 从消费到的最新的offset位置开始消费 earliest 从最初的offset位置开始消费，如果是新的topic话
         * none 从当前produce生产的最新的offset开始消费，默认
         */
        // props.put("auto.offset.reset","none");

        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "60000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
           /* props.put("enable.auto.commit", WebSrvUtil.srv("kafka.enable.auto.commit"));
            // 自动提交offset到zookeeper的时间间隔
            props.put("auto.commit.interval.ms", WebSrvUtil.srv("kafka.auto.commit.interval.ms"));
            props.put("session.timeout.ms", WebSrvUtil.srv("kafka.session.timeout.ms"));
            props.put("key.deserializer", WebSrvUtil.srv("kafka.key.deserializer"));
            props.put("value.deserializer", WebSrvUtil.srv("kafka.value.deserializer"));
            props.put("group.id", WebSrvUtil.srv("kafka.group.id"));*/

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        String a_topic = "test22";
        consumer.subscribe(Arrays.asList(a_topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(">>>>>> fetched from partition " + record.partition() + ", offset: " + record.offset());
                System.out.println(">>>>>> message: " + record.value());
                // 获取offset 相等

                try {
                        /*JsonObject obj = new JsonParser().parse(record.value()).getAsJsonObject();
                        System.out.println(">>>>>>>>>>>title:"+obj.get("titile").getAsString());
                        System.out.println(">>>>>>>>>>>>>>>>list:"+obj.get("json").getAsJsonArray().get(1));
                        System.out.println(">>>>>>>>>>>>>>>>list:"+obj.get("jsonobject").getAsJsonObject().toString());
                        SpringContextHolder.getBean(KafkaRetry.class).retrytest();*/
                    System.out.println(record);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    System.out.println(">>>>>>>>>>>>>>>> 数据poll异常");
                }


            }
        }
    }
}
