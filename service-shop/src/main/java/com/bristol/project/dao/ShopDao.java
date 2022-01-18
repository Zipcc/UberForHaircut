package com.bristol.project.dao;

import com.bristol.project.entity.Shop;
import com.bristol.project.entity.ShopServ;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ShopDao{

    Long create(Shop shop);

    Long createServ(ShopServ shopServ);

    int deleteShopByShopId(Long shopId);

    int deleteServiceByServiceId(Long serviceId);

    int updateShopByUsername(Shop shop);

    int updateShopService(ShopServ shopServ);

    Shop getShopByUsername(String username);

    Shop getShopByShopId(Long shopId);

    Shop getShopByShopName(String shopName);

    List<Shop> getSomeShop();

    List<Shop> getAllShop();

}
