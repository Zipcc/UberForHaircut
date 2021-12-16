package com.bristol.project.controller;

import com.bristol.project.entities.CommonResult;
import com.bristol.project.entities.Payment;
import com.bristol.project.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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
            return new CommonResult(200,"Successfully found data",payment);
        }else{
            return new CommonResult(444,"Failed to find data",null);
        }
    }

}
