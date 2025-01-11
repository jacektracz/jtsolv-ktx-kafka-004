package com.jtsolv.controller;

import com.jtsolv.dto.Employee;
import com.jtsolv.producer.KafkaAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
    @Autowired
    private KafkaAvroProducer producer;

    @PostMapping("/events")
    public String sendMessage(@RequestBody Employee employee) {
        producer.send(employee);
        return "message published !";
    }

    @GetMapping("/events")
    public String sendMessageByGet(@RequestParam int id) {
        Employee employee = new Employee();
        employee.setId("id");
        employee.setFirstName("first-name-"+  id) ;
        employee.setLastName("last-name-"+  id); ;
        employee.setMiddleName("midle-name-"+  id); ;
        producer.send(employee);
        return "message published !";
    }

}
