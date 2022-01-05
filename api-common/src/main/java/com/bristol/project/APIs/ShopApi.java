package com.bristol.project.APIs;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.entity.ShopServ;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//port:9200
@RequestMapping("/shops")
public interface ShopApi {

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PostMapping
    Result<Shop> create(@RequestBody Shop shop);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PostMapping("/services")
    Result<ShopServ> create(@RequestBody ShopServ shopServ);

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping ("/{shopId}")
    Result<Integer> deleteShopByShopId(@PathVariable("shopId") long shopId);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @DeleteMapping ("services/{serviceId}")
    Result<Integer> deleteServiceByServiceId(@PathVariable("serviceId") long serviceId);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PutMapping("/me")
    Result<Integer> updateCurrentShop(@RequestBody Shop shop);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PutMapping ("services/{serviceId}")
    Result<Integer> updateShopService(@RequestBody ShopServ shopServ);

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

}
