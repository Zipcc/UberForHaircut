package com.bristol.project.service.Impl;

import com.bristol.project.dao.BookingDao;
import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.openFeign.UserFeignApi;
import com.bristol.project.service.BookingService;
import com.bristol.project.utils.StatusCode;
import com.bristol.project.utils.TimeUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class BookingServiceImpl implements BookingService {

    @Resource
    private BookingDao bookingDao;

    @Resource
    private UserFeignApi userApi;
    @Override
    public Result<Appointment> create(Appointment appointment) {

        if(appointment.getAppointmentTime() == null || appointment.getClientUsername() == null
          || appointment.getBarberShopName() == null || appointment.getServiceName() == null){
            return new Result<>(StatusCode.CREATE_FAILED,"Create appointment failed, invalid message.");
        }
        appointment.setDeleted(false);
        appointment.setDone(false);
        appointment.setCanceled(false);

        //Generate id by timestamp and userId
        Long id = Long.parseLong((TimeUtil.getTime() + TimeUtil.getRandomNum()));
        appointment.setAppointmentId(id);
        int result = bookingDao.create(appointment);
        if(result > 0){
            Appointment newAppointment = bookingDao.getAppointmentById(id);
            return new Result<>(StatusCode.OK,"Create appointment:" + newAppointment + " successfully!");
        }else {
            return new Result<>(StatusCode.CREATE_FAILED,"Create appointment: " + appointment + " failed.");
        }
    }

    @Override
    public Result<Integer> updateAppointmentById(Long id, UPDATE_TYPE update_type) {

        Appointment appointment = bookingDao.getAppointmentById(id);
        String type = "";
        int result = -1;
        if(update_type == UPDATE_TYPE.COMPLETE){
            type = "complete";
            if(appointment.isDone()){
            return new Result<>(StatusCode.ALREADY_EXIST,"Appointment is already done.");
            }
            result = bookingDao.completeAppointmentById(id);
        }else if(update_type == UPDATE_TYPE.CANCEL){
            type = "cancel";
            if(appointment.isCanceled()){
                return new Result<>(StatusCode.ALREADY_EXIST,"Appointment is already canceled.");
            }
            result = bookingDao.cancelAppointmentById(id);
        }else if(update_type == UPDATE_TYPE.DELETE) {
            type = "delete";
            if (appointment.isCanceled()) {
                return new Result<>(StatusCode.ALREADY_EXIST, "Appointment is already deleted.");
            }
            result = bookingDao.deleteAppointmentById(id);
        }
            if(result > 0){
            return new Result<>(StatusCode.OK,type + " appointment successfully!");
        }else {
            return new Result<>(StatusCode.ERROR,type + " appointment failed.");
        }
    }

/*
    @Override
    public Result<Appointment> deleteAppointmentById(String id) {

        int result = bookingDao.deleteAppointmentById(id);
        if(result == 0){
            return new Result<>(StatusCode.NOT_EXIST,"Appointment: " + id + " not exist.");
        }
        return new Result<>(StatusCode.OK,"Appointment: " + id + " canceled successfully.");
    }

    @Override
    public Result<Appointment> getAppointmentById(String id) {

        Appointment appointment = bookingDao.getAppointmentById(id);
        if(appointment == null){
            return new Result<>(StatusCode.NOT_EXIST,"Appointment: " + id + " not exist.");
        }
        return new Result<>(StatusCode.OK,"Find appointment: " + id + " successfully.", appointment);
    }

    @Override
    public Result<List<Appointment>> getAllAppointment() {

        List<Appointment> list;
        list = bookingDao.getAllAppointment();
        if(list == null){
            return new Result<>(StatusCode.NOT_EXIST,"Appointment not exist.");
        }
        return new Result<>(StatusCode.OK, "Find " + list.size()+ " appointments", list);
    }

 */
}
