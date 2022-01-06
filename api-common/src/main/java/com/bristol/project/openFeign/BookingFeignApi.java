package com.bristol.project.openFeign;

import com.bristol.project.APIs.BookingApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//port:9300
@FeignClient(value = "service-booking")
@RequestMapping(value = "feign/bookings")
public interface BookingFeignApi extends BookingApi {
}