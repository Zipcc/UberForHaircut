package com.bristol.project.openFeign;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//port:9300
@RequestMapping("/bookings")
@FeignClient(value = "service-booking")
public interface BookingApi {

    //@PreAuthorize("hasAuthority('admin,barber')")
    @PostMapping
    Result<Appointment> create(@RequestBody Appointment appointment);

    //@PreAuthorize("hasAuthority('admin,barber')")
    @DeleteMapping("/{id}")
    Result<Appointment> deleteAppointmentById(@PathVariable("id") String id);

    //@PreAuthorize("hasAuthority('admin')")
    @GetMapping("/{username}")
    Result<Appointment> getAppointmentById(@PathVariable("username") String id);

    //@PreAuthorize("hasAuthority('admin')")
    @GetMapping
    Result<List<Appointment>> getAllAppointment();


}
