package com.bristol.project.openFeign;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//port:9200
@FeignClient(value = "service-shop", path = "/shops")
public interface ShopApi {

    @PreAuthorize("hasAnyAuthority('barber','admin')")
    @PostMapping
    Result<Integer> create(@RequestBody Shop shop);

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping ("/{username}")
    Result<Integer> deleteShopByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('barber','admin')")
    @PutMapping("/me")
    Result<Integer> updateCurrentShop(@RequestBody Shop shop);

    @PreAuthorize("hasAnyAuthority('admin')")
    @PutMapping("/{username}")
    Result<Integer> updateShopByUsername(@PathVariable("username") String username, @RequestBody Shop shop);

    @PreAuthorize("hasAnyAuthority('barber','admin')")
    @GetMapping("/me")
    Result<Shop> getCurrentShop();

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/{username}")
    Result<Shop> getShopByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping
    Result<List<Shop>> getAllShop();
}
