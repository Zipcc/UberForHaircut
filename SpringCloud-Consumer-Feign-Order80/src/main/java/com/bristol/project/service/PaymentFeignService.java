package com.bristol.project.service;

import com.bristol.project.entities.CommonResult;
import com.bristol.project.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payments/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
