package com.jtsolv.controller;

import com.jtsolv.dto.Employee;
import com.jtsolv.dto.User;
import com.jtsolv.producer.KafkaAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.StringWriter;
import java.io.PrintWriter;
@RestController
public class EventController {

    @Autowired
    private KafkaAvroProducer producer;

    @PostMapping("/events")
    public String sendMessage(@RequestBody Employee employee) {
        producer.sendEmploee(employee, "t-1");
        return "message published !";
    }

    @GetMapping("/create-emploee")
    public String sendMessageByGet(@RequestParam String topic, @RequestParam String id ) {
        try {
            dbg("START...");
            Employee employee = new Employee();
            employee.setId("id");
            employee.setFirstName("first-name-" + id);
            employee.setLastName("last-name-" + id);
            employee.setMiddleName("midle-name-" + id);
            employee.setEmailId("email-" + id);
            dbg("BEFORE-SEND...");
            producer.sendEmploee(employee, topic);
            dbg("AFTER-SEND-END...");
            return "message published !";
        }catch (Exception ex) {
            dbg("ERROR(error-in-create-emploee):" + ex.getMessage());
            dbg("ERROR(in-create-emploee):" + getStackTraceAsString(ex));
            return "error-in-create-emploee" + ex.getMessage();
        }
    }

    @GetMapping("/create-user")
    public String sendUser(@RequestParam String topic, @RequestParam String id ) {
        try {
            dbg("START...");
            User employee = new User();
            employee.setId("id");
            employee.setFirstName("first-name-" + id);
            employee.setLastName("last-name-" + id);;
            employee.setMiddleName("midle-name-" + id);;
            employee.setEmailId("email-" + id);;
            dbg("BEFORE-SEND...");
            producer.sendUser(employee, topic);
            dbg("AFTER-SEND-END...");
            return "message published !";
        }catch (Exception ex) {
            dbg("ERROR(in-create-user):" + ex.getMessage());
            dbg("ERROR(in-create-user):" + getStackTraceAsString(ex));
            return "error-in-create-user" + ex.getMessage();
        }
    }

    private static void dbg(String txt){
        System.out.println(txt);
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString(); // Return the stack trace as a string
    }
}
