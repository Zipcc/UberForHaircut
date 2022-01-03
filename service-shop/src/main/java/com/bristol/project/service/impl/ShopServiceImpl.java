package com.bristol.project.service.impl;

import com.bristol.project.dao.ShopDao;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
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
        //Not exist
        if(shopDao.getShopByUsername(username) == null){
            int shopId = shopDao.create(shop);
            if (shopId > 0) {
                //Success
                return new Result<>(StatusCode.OK,"Shop of user: " + shop.getUsername() + " created successfully.", shop);
            }else{
                //Create failed
                return new Result<>(StatusCode.CREATE_FAILED,"Failed to create shop.");
            }
        //Already exist
        }else{
            //Update
            return updateShopByUsername(username, shop);
        }
    }

    @Override
    public Result<Shop> updateShopByUsername(String username, Shop shop) {

        Shop oldShop = shopDao.getShopByUsername(username);
        if(oldShop == null){
            return new Result<>(StatusCode.NOT_EXIST,"Shop of" + username + " not exist.");
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
        if(shop.getPhoneNumber()==null){
            shop.setPhoneNumber(oldShop.getPhoneNumber());
        }

        int result = shopDao.updateShopByUsername(username, shop);
        if (result > 0) {
            //Success.
            return new Result<>(StatusCode.OK, "Update successfully!", shop);
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
    public Result<List<Shop>> getAllShop() {

        List<Shop> list;
        list = shopDao.getAllShop();
        if(list == null){
            return new Result<>(StatusCode.NOT_EXIST,"Shop not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + list.size()+ " shops", list);
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