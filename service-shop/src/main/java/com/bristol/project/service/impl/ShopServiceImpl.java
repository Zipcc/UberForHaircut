package com.bristol.project.service.impl;

import com.bristol.project.dao.ShopDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.entity.ShopServ;
import com.bristol.project.service.ShopService;
import com.bristol.project.utils.StatusCode;
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
        //Already exist
        if(shopDao.getShopByUsername(username) != null){
            return new Result<>(StatusCode.ALREADY_EXIST,"Shop of user: " + shop.getUsername() + " already exists.");
        }
        //Shop not exist
        long shopId = shopDao.create(shop);
        if (shopId > 0) {
            //Success
            shop.setShopId(shopId);
            return new Result<>(StatusCode.OK,"Shop of user: " + shop.getUsername() + " created successfully.", shop);
        }else{
            //Create failed
            return new Result<>(StatusCode.CREATE_FAILED,"Failed to create shop.");
        }
    }

    @Override
    public Result<ShopServ> createServ(ShopServ shopServ) {

        long shopServId = shopDao.createServ(shopServ);
        if (shopServId > 0) {
            //Success
            shopServ.setServiceId(shopServId);
            return new Result<>(StatusCode.OK,"Shop of user: " + shopServ.getServiceName() + " created successfully.", shopServ);
        }else{
            //Create failed
            return new Result<>(StatusCode.CREATE_FAILED,"Failed to create service.");
        }
    }

    @Override
    public Result<Integer> deleteShopByShopId(long shopId) {

        if(shopDao.getShopByShopId(shopId) == null) {
            return new Result<>(StatusCode.NOT_EXIST,"Shop : " + shopId + " not exists.");
        }else{
            int result = shopDao.deleteShopByShopId(shopId);
            if (result > 0) {
                //Success.
                return new Result<>(StatusCode.OK, "Delete shop successfully!", result);
            }else{
                //Create failed.
                return new Result<>(StatusCode.ERROR,"Failed to delete shop.");
            }
        }
    }

    @Override
    public Result<Integer> deleteServiceByServiceId(long serviceId) {

        int result = shopDao.deleteServiceByServiceId(serviceId);
        if (result > 0) {
            //Success.
            return new Result<>(StatusCode.OK, "Delete service successfully!", result);
        }else{
            //Create failed.
            return new Result<>(StatusCode.ERROR,"Failed to delete service.");
        }
    }

    @Override
    public Result<Integer> updateShopService(ShopServ shopServ) {

        int result = shopDao.updateShopService(shopServ);
        if (result > 0) {
            //Success.
            return new Result<>(StatusCode.OK, "Update service successfully!", result);
        }else{
            //Create failed.
            return new Result<>(StatusCode.ERROR,"Failed to update service.");
        }
    }

    @Override
    public Result<Integer> updateShopByUsername(String username, Shop shop) {

        Shop oldShop = shopDao.getShopByUsername(username);
        if(oldShop == null){
            return new Result<>(StatusCode.NOT_EXIST,"Shop of user: " + username + " not exist.");
        }
        if(shop.getShopName()==null){
            shop.setShopName(oldShop.getShopName());
        }
        if(shop.getLocationDescription()==null){
            shop.setLocationDescription(oldShop.getLocationDescription());
        }
        if(shop.getServiceForGender()==null){
            shop.setServiceForGender(oldShop.getServiceForGender());
        }
        if(shop.getShopServs().size() != oldShop.getShopServs().size()){
            shop.setShopServs(oldShop.getShopServs());
        }
        int result = shopDao.updateShopByUsername(shop);
        if (result > 0) {
            //Success.
            return new Result<>(StatusCode.OK, "Update successfully!", result);
        }else{
            //Update failed.
            return new Result<>(StatusCode.CREATE_FAILED,"Failed to update.");
        }
    }

    @Override
    public Result<Shop> getShopByUsername(String username) {

        Shop shop = shopDao.getShopByUsername(username);

        if (shop == null){
            return new Result<>(StatusCode.NOT_EXIST,"User: " + username + " doesn't hold a shop.");
        }
        return new Result<>(StatusCode.OK,"Find shop of " + username + " successfully!", shop);
    }

    @Override
    public Result<List<Shop>> getSomeShop() {

        List<Shop> someList;
        someList = shopDao.getSomeShop();
        if(someList == null){
            return new Result<>(StatusCode.NOT_EXIST,"Shop not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + someList.size()+ " shops", someList);    }

    @Override
    public Result<List<Shop>> getAllShop() {

        List<Shop> allList;
        allList = shopDao.getAllShop();
        if(allList == null){
            return new Result<>(StatusCode.NOT_EXIST,"Shop not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + allList.size()+ " shops", allList);
    }

  /*  @Override
    public Shop getShopByUsername(String username) {

        Shop shop = shopDao.getShopByUsername(username);
        if (shop == null){
            return new Result<>(StatusCode.NOT_EXIST,"User -> " + username + " <- not exist.");
        }
        return new Result<>(StatusCode.OK,"Find user -> " + username + " <- successfully!", user);

    }

    @Override
    public Result<List<Shop>> getAllShop() {
        return null;
    }

    @Override
    public Result<User> getUserByUsername(String username) {

        User user = userDao.getUserByUsername(username);

        if (user == null){
            return new Result<>(StatusCode.NOT_EXIST,"User -> " + username + " <- not exist.");
        }
        return new Result<>(StatusCode.OK,"Find user -> " + username + " <- successfully!", user);
    }

    @Override
    public Result<List<User>> getAllUser() {
        List<User> list = new ArrayList<>();
        list = userDao.getAllUser();
        if(list == null){
            return new Result<>(StatusCode.NOT_EXIST,"User not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + list.size()+ " users", list);
    }*/
}
