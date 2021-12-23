package com.bristol.project.controller;

import com.bristol.project.entities.CommonResult;
import com.bristol.project.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {



    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payments/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        return "Nacos, serverPort" + serverPort +"id" + id;
    }
/*
    @PostMapping(value = "/payments")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200,"Successfully inserted data at serverPort" + serverPort,result);
        }else{
            return new CommonResult(444,"Failed to insert data",null);
        }
    }
    @GetMapping(value = "/payments/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = paymentService.getPaymentById(id);

        if (payment != null) {
            return new CommonResult(200,"Successfully found data --" + serverPort,payment);
        }else{
            return new CommonResult(444,"Failed to find data",null);
        }
    }
*/

}