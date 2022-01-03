package com.bristol.project.controller;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.openFeign.ShopApi;
import com.bristol.project.utils.StatusCode;
import com.bristol.project.utils.TokenDecoder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RestController;
import com.bristol.project.service.ShopService;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class ShopController implements ShopApi {

    @Resource
    private ShopService shopService;

    @Override
    public Result<Shop> create(Shop shop) {

        if(shop == null || shop.getUsername() == null || shop.getShopName().trim().isEmpty()){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter shopName.");
        }
        return shopService.create(shop);
    }

    @PreAuthorize("hasAuthority('admin')")
    @Override
    public Result<Shop> getShopByUsername(String username) {

        return shopService.getShopByUsername(username);
    }

    @PreAuthorize("hasAuthority('admin')")
    @Override
    public Result<List<Shop>> getAllShop() {
        return shopService.getAllShop();
    }



 /*   @GetMapping(value = "/payments/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){

        Payment payment = userService.getPaymentById(id);

        if (payment != null) {
            return new CommonResult(200,"Successfully found data --" + serverPort,payment);
        }else{
            return new CommonResult(444,"Failed to find data",null);
        }
    }

    @GetMapping(value = "/payments/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        return services.get(0);
    }
    */
}
