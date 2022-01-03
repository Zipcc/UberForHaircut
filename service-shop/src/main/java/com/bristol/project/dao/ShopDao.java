package com.bristol.project.dao;

import com.bristol.project.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ShopDao{

    int create(Shop shop);

    int updateShopByUsername(String username, @Param("shop") Shop shop);

    Shop getShopByUsername(String username);

    List<Shop> getAllShop();
}
