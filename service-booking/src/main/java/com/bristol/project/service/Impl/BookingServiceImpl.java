package com.bristol.project.service.Impl;

import com.bristol.project.dao.BookingDao;
import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.User;
import com.bristol.project.openFeign.UserApi;
import com.bristol.project.service.BookingService;
import com.bristol.project.utils.StatusCode;
import com.bristol.project.utils.TimeUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Resource
    private BookingDao bookingDao;

    @Resource
    private UserApi userApi;
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
        System.out.println(id+"11111111111111111111111111111111");
        appointment.setId(id);
        int result = bookingDao.create(appointment);
        if(result < 0){
            return new Result<>(StatusCode.CREATE_FAILED,"Create appointment: " + appointment + " failed.");
        }else {
            return new Result<>(StatusCode.OK,"Create appointment:" + appointment + " successfully!");
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
