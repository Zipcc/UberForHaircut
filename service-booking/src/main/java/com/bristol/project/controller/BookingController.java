package com.bristol.project.controller;

import com.bristol.project.APIs.BookingApi;
import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Role;
import com.bristol.project.service.BookingService;
import com.bristol.project.entity.StatusCode;
import com.bristol.project.utils.StringUtil;
import com.bristol.project.utils.TokenDecoder;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookingController implements BookingApi {

    @Resource
    BookingService bookingService;

    @Override
    public Result<Appointment> create(Appointment appointment) {

        if(appointment == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment.");
        }
        return bookingService.create(appointment);
    }

    @Override
    public Result<Integer> destroyAppointmentById(Long appointmentId) {

        if(appointmentId == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
        }
        return bookingService.destroyAppointmentById(appointmentId);
    }

    @Override
    public Result<Integer> deleteAppointmentById(Long appointmentId) {

        if(appointmentId == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
        }
        int role = TokenDecoder.tokenRole();
        if(role == Role.ADMIN_NUM){
            return new Result<>(StatusCode.ERROR, "Admins could destroy records.");
        }
        String username = TokenDecoder.tokenUsername();
            return bookingService.updateAppointmentById(appointmentId, BookingService.UPDATE_TYPE.DELETE, username);
    }

    @Override
    public Result<Integer> completeAppointmentById(Long appointmentId) {

        if(appointmentId == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
        }
        String username = TokenDecoder.tokenUsername();
        return bookingService.updateAppointmentById(appointmentId, BookingService.UPDATE_TYPE.COMPLETE, username);
    }

    @Override
    public Result<Integer> cancelAppointmentById(Long appointmentId) {

        if(appointmentId == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
        }
        String username = TokenDecoder.tokenUsername();
        return bookingService.updateAppointmentById(appointmentId, BookingService.UPDATE_TYPE.CANCEL,username);
    }

    @Override
    public Result<List<Appointment>> getAllAppointmentByUsername(String username) {

        if(StringUtil.notExist(username)){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return bookingService.getAllAppointmentByUsername(username);
    }

    @Override
    public Result<List<Appointment>> getAllCurrentAppointment() {

        String username = TokenDecoder.tokenUsername();
        if(StringUtil.notExist(username)){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter username.");
        }
        return bookingService.getAllCurrentAppointment(username);
    }
}