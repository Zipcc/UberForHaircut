package com.bristol.project.openFeign;

import com.bristol.project.APIs.ShopApi;
import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//port:9200
@FeignClient(value = "service-shop")
@RequestMapping(value = "feign/shops")
public interface ShopFeignApi extends ShopApi {
/*
    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PostMapping
    Result<Shop> create(@RequestBody Shop shop);

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping ("/{shopId}")
    Result<Integer> deleteShopByShopId(@PathVariable("shopId") long shopId);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PutMapping("/me")
    Result<Integer> updateCurrentShop(@RequestBody Shop shop);

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping("/{username}")
    Result<Integer> updateShopByUsername(@PathVariable("username") String username, @RequestBody Shop shop);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @GetMapping("/me")
    Result<Shop> getCurrentShop();

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/{username}")
    Result<Shop> getShopByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('admin','client','barber')")
    @GetMapping("/explore")
    Result<List<Shop>> getSomeShop();

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping
    Result<List<Shop>> getAllShop();

    @PreAuthorize("hasAnyAuthority('admin','client')")
    @PostMapping("/bookings/{serviceId}")
    Result<Appointment> createBookingsByServiceId(@PathVariable("serviceId") long serviceId);

 */
}
