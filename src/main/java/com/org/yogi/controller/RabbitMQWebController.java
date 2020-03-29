package com.org.yogi.controller;

import com.org.yogi.model.Employee;
import com.org.yogi.rempository.EmployeeRepo;
import com.org.yogi.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
/*
 * created by yogi
 * */

@RestController
@RequestMapping(value = "/yogi-rabbitmq/")
public class RabbitMQWebController {

    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping(value = "/producer/{empAddress}")
    public String producer(@PathVariable String empAddress) {
        System.out.println("getting data from db");

        rabbitMQSender.send(employeeRepo.findByEmpAddress(empAddress));

        return "Message sent to the RabbitMQ Successfully";
    }

    @PostMapping(value = "/save")
    public String saveDataIntoDb(@RequestBody Employee employee) {

        employeeRepo.save(employee);

        return "Message save to the mongo db Successfully";
    }
}

