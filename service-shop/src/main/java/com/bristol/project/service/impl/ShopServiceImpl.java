package com.bristol.project.service.impl;

import com.bristol.project.dao.ShopDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.entity.ShopServ;
import com.bristol.project.service.ShopService;
import com.bristol.project.entity.StatusCode;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopDao shopDao;

    @Override
    public Result<Shop> create(Shop shop) {

        String username = shop.getUsername();
        //already exist
        if (shopDao.getShopByUsername(username) != null) {
            return new Result<>(StatusCode.ALREADY_EXIST, "Shop of user: " + shop.getUsername() + " already exists.");
        }
        if (shopDao.getShopByShopName(shop.getShopName()) != null) {
            return new Result<>(StatusCode.ALREADY_EXIST, "Shop name: " + shop.getShopName() + " already exists.");
        }
        //shop not exist
        shopDao.create(shop);
        Long shopId = shop.getShopId();
        if (shopId != null) {
            //success
            shop.setShopId(shopId);
            return new Result<>(StatusCode.OK, "Shop of user: " + shop.getUsername() + " created successfully.", shop);
        } else {
            //create failed
            return new Result<>(StatusCode.CREATE_FAILED, "Failed to create shop.");
        }
    }

    @Override
    public Result<ShopServ> createServ(ShopServ shopServ) {

        shopDao.createServ(shopServ);
        Long shopServId = shopServ.getServiceId();
        if (shopServId != null) {
            //success
            shopServ.setServiceId(shopServId);
            return new Result<>(StatusCode.OK, "Service: " + shopServ.getServiceName() + " created successfully.", shopServ);
        } else {
            //create failed
            return new Result<>(StatusCode.CREATE_FAILED, "Failed to create service.");
        }
    }

    @Override
    public Result<Integer> deleteShopByShopId(Long shopId) {

        if (shopDao.getShopByShopId(shopId) == null) {
            return new Result<>(StatusCode.NOT_EXIST, "Shop : " + shopId + " not exists.");
        } else {
            int result = shopDao.deleteShopByShopId(shopId);
            if (result > 0) {
                //success.
                return new Result<>(StatusCode.OK, "Delete shop successfully!");
            } else {
                //create failed.
                return new Result<>(StatusCode.ERROR, "Failed to delete shop.");
            }
        }
    }

    @Override
    public Result<Integer> deleteServiceByServiceId(Long serviceId) {

        int result = shopDao.deleteServiceByServiceId(serviceId);
        if (result > 0) {
            //success.
            return new Result<>(StatusCode.OK, "Delete service successfully!");
        } else {
            //create failed.
            return new Result<>(StatusCode.ERROR, "Failed to delete service.");
        }
    }

    @Override
    public Result<Integer> updateShopService(String username, ShopServ shopServ) {

        Shop shop = shopDao.getShopByUsername(username);
        List<ShopServ> shopServList = shop.getShopServs();
        boolean hasService = false;
        for(ShopServ s:shopServList){
            if (s.getServiceId().equals(shopServ.getServiceId())) {
                hasService = true;
                break;
            }
        }
        if(!hasService){
            return new Result<>(StatusCode.NOT_EXIST,"Shop service not exist in your shop.");
        }
        int result = shopDao.updateShopService(shopServ);
        if (result > 0) {
            //success.
            return new Result<>(StatusCode.OK, "Update service successfully!");
        } else {
            //create failed.
            return new Result<>(StatusCode.ERROR, "Failed to update service.");
        }
    }

    @Override
    public Result<Integer> updateShopByUsername(String username, Shop shop) {

        Shop oldShop = shopDao.getShopByUsername(username);
        if (oldShop == null) {
            return new Result<>(StatusCode.NOT_EXIST, "Shop of user: " + username + " not exist.");
        }
        if (shop.getShopName() == null) {
            shop.setShopName(oldShop.getShopName());
        }else{
            if (!shopDao.getShopByShopName(shop.getShopName()).getUsername().equals(username)) {
                return new Result<>(StatusCode.ALREADY_EXIST, "Shop name: " + shop.getShopName() + " already exists.");
            }
        }
        if (shop.getLocationDescription() == null) {
            shop.setLocationDescription(oldShop.getLocationDescription());
        }
        if (shop.getServiceForGender() == null) {
            shop.setServiceForGender(oldShop.getServiceForGender());
        }
        if (shop.getShopServs() == null ||shop.getShopServs().size() != oldShop.getShopServs().size()) {
            shop.setShopServs(oldShop.getShopServs());
        }
        int result = shopDao.updateShopByUsername(shop);
        if (result > 0) {
            //Success.
            return new Result<>(StatusCode.OK, "Update successfully!");
        } else {
            //Update failed.
            return new Result<>(StatusCode.ERROR, "Failed to update.");
        }
    }

    @Override
    public Result<Shop> getShopByUsername(String username) {

        Shop shop = shopDao.getShopByUsername(username);
        if (shop == null) {
            return new Result<>(StatusCode.NOT_EXIST, "User: " + username + " doesn't hold a shop.");
        }
        return new Result<>(StatusCode.OK, "Find shop of " + username + " successfully!", shop);
    }

    @Override
    public Result<List<Shop>> getSomeShop() {

        List<Shop> someList;
        someList = shopDao.getSomeShop();
        if (someList == null) {
            return new Result<>(StatusCode.NOT_EXIST, "Shop not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + someList.size() + " shops", someList);
    }

    @Override
    public Result<List<Shop>> getAllShop() {

        List<Shop> allList;
        allList = shopDao.getAllShop();
        if (allList == null) {
            return new Result<>(StatusCode.NOT_EXIST, "Shop not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + allList.size() + " shops", allList);
    }
}