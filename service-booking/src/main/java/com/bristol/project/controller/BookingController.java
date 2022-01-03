package com.bristol.project.controller;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import com.bristol.project.openFeign.BookingApi;
import com.bristol.project.service.BookingService;
import com.bristol.project.utils.StatusCode;
import javax.annotation.Resource;
import java.util.List;

public class BookingController implements BookingApi {

    @Resource
    BookingService bookingService;

    @Override
    public Result<Appointment> create(Appointment appointment) {

        return null;
    }

    @Override
    public Result<Appointment> deleteAppointmentById(String id) {

        if(id == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
        }
        return bookingService.deleteAppointmentById(id);
    }

    @Override
    public Result<Shop> getAppointmentById(String id) {
        return null;
    }

    @Override
    public Result<List<Appointment>> getAllAppointment() {
        return null;
    }

}
