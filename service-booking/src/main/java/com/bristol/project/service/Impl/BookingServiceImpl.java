package com.bristol.project.service.Impl;

import com.bristol.project.dao.BookingDao;
import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.service.BookingService;
import com.bristol.project.utils.StatusCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Resource
    private BookingDao bookingDao;

    @Override
    public Result<Appointment> create(Appointment appointment) {

        Date date = new Date();
        System.out.println(date);

        int appointmentId = bookingDao.create(appointment);
        if(appointmentId == 0){
            return new Result<>(StatusCode.CREATE_FAILED,"Create appointment: " + appointment + " failed.");
        }
        return null;
    }

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
}
