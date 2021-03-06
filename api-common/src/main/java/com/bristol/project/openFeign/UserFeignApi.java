package com.bristol.project.openFeign;

import com.bristol.project.APIs.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-user")
@RequestMapping(value = "feign/users")
public interface UserFeignApi extends UserApi {}
