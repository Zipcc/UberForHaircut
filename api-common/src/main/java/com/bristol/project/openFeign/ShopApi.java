package com.bristol.project.openFeign;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//port:9200
@RequestMapping("/shops")
@FeignClient(value = "service-shop")
public interface ShopApi {

    @PostMapping
    Result<Shop> create(@RequestBody Shop shop);

    //@PreAuthorize("hasAuthority('admin')")
    @GetMapping("/{username}")
    Result<Shop> getShopByUsername(@PathVariable("username") String username);

    //@PreAuthorize("hasAuthority('admin')")
    @GetMapping
    Result<List<Shop>> getAllShop();
}
