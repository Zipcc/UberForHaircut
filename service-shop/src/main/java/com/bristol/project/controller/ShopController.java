package com.bristol.project.controller;

import com.bristol.project.APIs.ShopApi;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.entity.ShopServ;
import com.bristol.project.utils.StatusCode;
import com.bristol.project.utils.StringUtil;
import com.bristol.project.utils.TokenDecoder;
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

        if(shop == null || StringUtil.notExist(shop.getShopName())){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter shopName.");
        }
        String currentUsername = TokenDecoder.tokenUsername();
        shop.setUsername(currentUsername);
        return shopService.create(shop);
    }

    @Override
    public Result<ShopServ> create(ShopServ shopServ) {

        if(shopServ == null || StringUtil.notExist(shopServ.getServiceName())){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter shopService.");
        }
        String currentUsername = TokenDecoder.tokenUsername();
        Shop shop = shopService.getShopByUsername(currentUsername).getData();
        if(shop == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please create your shop.");
        }
        Long shopId = shop.getShopId();
        shopServ.setShopId(shopId);
        return shopService.createServ(shopServ);
    }

    @Override
    public Result<Integer> deleteShopByShopId(Long shopId) {

        if(shopId == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter shopId.");
        }
        return shopService.deleteShopByShopId(shopId);
    }

    @Override
    public Result<Integer> deleteServiceByServiceId(Long serviceId) {

        if(serviceId == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter serviceId.");
        }
        return shopService.deleteServiceByServiceId(serviceId);
    }

    @Override
    public Result<Integer> updateCurrentShop(Shop shop) {

        if(shop == null){
            return new Result<>(StatusCode.NOT_EXIST,"Shop not exist.");
        }
        String currentUsername = TokenDecoder.tokenUsername();
        shop.setUsername(currentUsername);
        return shopService.updateShopByUsername(currentUsername, shop);
    }

    @Override
    public Result<Integer> updateShopService(ShopServ shopServ) {

        if(shopServ == null || shopServ.getShopId() == 0){
            return new Result<>(StatusCode.NOT_EXIST,"Shop service not exist.");
        }
        return shopService.updateShopService(shopServ);
    }

    @Override
    public Result<Integer> updateShopByUsername(String username, Shop shop) {

        if(StringUtil.notExist(username) || shop == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        shop.setUsername(username);
        return shopService.updateShopByUsername(username, shop);
    }

    @Override
    public Result<Shop> getCurrentShop() {

        return shopService.getShopByUsername(TokenDecoder.tokenUsername());
    }

    @Override
    public Result<Shop> getShopByUsername(String username) {

        if(StringUtil.notExist(username)){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return shopService.getShopByUsername(username);
    }

    @Override
    public Result<List<Shop>> getSomeShop() {

        return shopService.getSomeShop();
    }

    @Override
    public Result<List<Shop>> getAllShop() {

        return shopService.getAllShop();
    }
}
