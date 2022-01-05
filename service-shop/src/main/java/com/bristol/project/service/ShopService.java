package com.bristol.project.service;

import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.entity.ShopServ;
import com.bristol.project.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ShopService {

    Result<Shop> create(Shop shop);

    Result<ShopServ> createServ(ShopServ shopServ);

    Result<Integer> deleteShopByShopId(long shopId);

    Result<Integer> deleteServiceByServiceId(long serviceId);

    Result<Integer> updateShopService(ShopServ shopServ);

    Result<Integer> updateShopByUsername(String username, Shop shop);

    Result<Shop> getShopByUsername(String username);

    Result<List<Shop>> getSomeShop();

    Result<List<Shop>> getAllShop();
}
