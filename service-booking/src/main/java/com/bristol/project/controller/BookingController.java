package com.bristol.project.controller;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.openFeign.BookingApi;
import com.bristol.project.service.BookingService;
import com.bristol.project.utils.StatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/bookings")
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

  //  @PreAuthorize("hasAnyAuthority('admin','barber')")
  //  @Override
  //  public Result<Appointment> deleteAppointmentById(String id) {
//
  //      if(id == null){
  //          return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
  //      }
  //      return bookingService.deleteAppointmentById(id);
  //  }
//
  //  @PreAuthorize("hasAuthority('admin')")
  //  @Override
  //  public Result<Appointment> getAppointmentById(String id) {
//
  //      if (id == null) {
  //          return new Result<>(StatusCode.NOT_EXIST, "Please enter appointment id.");
  //      }
  //      return bookingService.getAppointmentById(id);
  //  }
//
  //  @PreAuthorize("hasAnyAuthority('admin','client')")
  //  @Override
  //  public Result<List<Appointment>> getAllAppointment() {
//
  //      return bookingService.getAllAppointment();
  //  }

}
