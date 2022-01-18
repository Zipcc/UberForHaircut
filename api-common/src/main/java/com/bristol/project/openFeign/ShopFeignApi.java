package com.bristol.project.openFeign;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-shop")
public interface ShopFeignApi {

    @GetMapping("/shops/{username}")
    Result<Shop> getShopByUsername(@PathVariable("username") String username);
}