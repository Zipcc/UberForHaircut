package com.bristol.project.openFeign;

import com.bristol.project.APIs.ShopApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//port:9200
@FeignClient(value = "service-shop")
@RequestMapping(value = "feign/shops")
public interface ShopFeignApi extends ShopApi {
}
