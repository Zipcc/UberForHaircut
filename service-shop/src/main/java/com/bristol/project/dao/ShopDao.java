package com.bristol.project.dao;

import com.bristol.project.entity.Shop;
import com.bristol.project.entity.ShopServ;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ShopDao{

    long create(Shop shop);

    long createServ(ShopServ shopServ);

    int deleteShopByShopId(long shopId);

    int deleteServiceByServiceId(long serviceId);

    int updateShopByUsername(Shop shop);

    int updateShopService(ShopServ shopServ);

    Shop getShopByUsername(String username);

    Shop getShopByShopId(long shopId);

    List<Shop> getSomeShop();

    List<Shop> getAllShop();

}
