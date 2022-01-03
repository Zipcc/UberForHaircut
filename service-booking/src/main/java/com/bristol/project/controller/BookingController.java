package com.bristol.project.controller;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.openFeign.BookingApi;
import com.bristol.project.service.BookingService;
import com.bristol.project.utils.StatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookingController implements BookingApi {

    @Resource
    BookingService bookingService;

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @Override
    public Result<Appointment> create(Appointment appointment) {

        if(appointment == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment.");
        }
        return bookingService.create(appointment);
    }

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @Override
    public Result<Appointment> deleteAppointmentById(String id) {

        if(id == null){
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
        }
        return bookingService.deleteAppointmentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @Override
    public Result<Appointment> getAppointmentById(String id) {

        if (id == null) {
            return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
        }
        return bookingService.getAppointmentById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @Override
    public Result<List<Appointment>> getAllAppointment() {

        return bookingService.getAllAppointment();
    }

}
