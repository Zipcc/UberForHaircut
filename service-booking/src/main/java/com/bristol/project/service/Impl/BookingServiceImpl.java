package com.bristol.project.service.Impl;

import com.bristol.project.dao.BookingDao;
import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.entity.ShopServ;
import com.bristol.project.openFeign.ShopFeignApi;
import com.bristol.project.service.BookingService;
import com.bristol.project.entity.StatusCode;
import com.bristol.project.utils.StringUtil;
import com.bristol.project.utils.TimeUtil;
import com.bristol.project.utils.TokenDecoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Resource
    private BookingDao bookingDao;

    @Resource
    private ShopFeignApi shopFeignApi;

    @Override
    public Result<Appointment> create(Appointment appointment) {

        String clientUsername = appointment.getClientUsername();
        String barberUsername = appointment.getBarberUsername();
        String serviceName = appointment.getServiceName();
        String appointmentTime = appointment.getAppointmentTime();
        if(StringUtil.notExist(appointmentTime) || StringUtil.notExist(clientUsername)
                || StringUtil.notExist(barberUsername) || StringUtil.notExist(serviceName)) {
            return new Result<>(StatusCode.CREATE_FAILED,"Create appointment failed, invalid message.");
        }

        //generate id by timestamp and userId
        Long appointmentId = Long.parseLong((TimeUtil.getTime() + TimeUtil.getRandomNum()));
        appointment.setAppointmentId(appointmentId);

        Shop shop = shopFeignApi.getShopByUsername(barberUsername).getData();
        if(shop == null){
            return new Result<>(StatusCode.NOT_EXIST,"Shop of barber: " + barberUsername + "  not exist!");
        }
        appointment.setBarberShopName(shop.getShopName());

        //find service description
        String serviceDescription = "Updated";

        List<ShopServ> shopServs = shop.getShopServs();
        for(ShopServ s : shopServs){
            if(s.getServiceName().equals(serviceName)){
                serviceDescription = s.getDescription();
            }
        }
        appointment.setServiceDescription(serviceDescription);
        appointment.setDone(false);
        appointment.setCanceled(false);

        // id is not generated by database, so we get result which indicates number of created record lines
        int result = bookingDao.create(appointment);
        if(result == 1){
            Appointment newAppointment = bookingDao.getAppointmentById(appointmentId);
            newAppointment.setBarberShopName(appointment.getBarberShopName());
            return new Result<>(StatusCode.OK,"Create appointment:" + newAppointment.getAppointmentId() + " successfully!", newAppointment);
        }else {
            return new Result<>(StatusCode.CREATE_FAILED,"Create appointment: " + appointment + " failed.");
        }
    }

    @Override
    public Result<Integer> destroyAppointmentById(Long appointmentId) {

        if(bookingDao.getAppointmentById(appointmentId) == null){
            return new Result<>(StatusCode.NOT_EXIST,"Appointment:" + appointmentId + " not exist!");
        }
        int result = bookingDao.destroyAppointmentById(appointmentId);
        if(result > 0){
            return new Result<>(StatusCode.OK,"Destroy appointment:" + appointmentId + " successfully!");
        }else {
            return new Result<>(StatusCode.ERROR,"Destroy appointment: " + appointmentId + " failed.");
        }
    }

    @Override
    public Result<Integer> updateAppointmentById(Long appointmentId, UPDATE_TYPE update_type, String username) {

        Appointment appointment = bookingDao.getAppointmentById(appointmentId);
        String type = "";
        int result = -1;
        if(update_type == UPDATE_TYPE.COMPLETE){
            type = "complete";
            if(appointment.isDone()){
            return new Result<>(StatusCode.ALREADY_EXIST,"Appointment is already done.");
            }
            result = bookingDao.completeAppointmentById(appointmentId);
        }else if(update_type == UPDATE_TYPE.CANCEL){
            type = "cancel";
            if(appointment.isCanceled()){
                return new Result<>(StatusCode.ALREADY_EXIST,"Appointment is already canceled.");
            }
            result = bookingDao.cancelAppointmentById(appointmentId);
        }else if(update_type == UPDATE_TYPE.DELETE) {
            type = "delete";
            result = bookingDao.deleteAppointmentById(appointmentId, username);
        }
        if(result > 0){
            return new Result<>(StatusCode.OK,type + " appointment successfully!");
        }else {
            return new Result<>(StatusCode.ERROR,type + " appointment failed.");
        }
    }

    @Override
    public Result<List<Appointment>> getAllAppointmentByUsername(String username) {

        if(StringUtil.notExist(username)){
            return new Result<>(StatusCode.NOT_EXIST," username not exist!");
        }
        List<Appointment> allList;
        allList = bookingDao.getAllAppointmentByUsername(username);
        if (allList == null || allList.size() == 0) {
            return new Result<>(StatusCode.NOT_EXIST, "Appointment not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + allList.size() + " appointments", allList);
    }

    @Override
    public Result<List<Appointment>> getAllCurrentAppointment(String username) {

        if(StringUtil.notExist(username)){
            return new Result<>(StatusCode.NOT_EXIST," username not exist!");
        }
        List<Appointment> currentIdList;
        List<Appointment> resultList = new ArrayList<>();
        currentIdList = bookingDao.getAllUserAppointmentIdByUsername(username);
        if (currentIdList == null || currentIdList.size() == 0) {
            return new Result<>(StatusCode.NOT_EXIST, "Appointment not exist.");
        }
        for(Appointment id : currentIdList){
            resultList.add(bookingDao.getAppointmentById(id.getAppointmentId()));
        }
        return new Result<>(StatusCode.OK, "Find " + resultList.size() + " appointments", resultList);
    }
}
