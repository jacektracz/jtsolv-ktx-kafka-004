package com.jtsolv.producer;

import com.jtsolv.dto.Employee;
import com.jtsolv.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaAvroProducer {

    @Value("${topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Employee> template;

    @Autowired
    private KafkaTemplate<String, User> templateUser;

    public void sendEmploee(Employee employee,String topic){

        CompletableFuture<SendResult<String, Employee>> future = template.send(topic, UUID.randomUUID().toString(),employee);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                dbg("Sent message=[" + employee +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                dbg("Unable to send message=[" +
                        employee + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendUser(User user,String topic){

        dbg("Send to topic:" + topic);

        CompletableFuture<SendResult<String, User>> future = templateUser.send(topic, UUID.randomUUID().toString(), user);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                dbg("Sent message=[" + user +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                dbg("Unable to send message=[" +
                        user + "] due to : " + ex.getMessage());
            }
        });
    }
    private static void dbg(String txt){
        System.out.println(txt);
    }

}
