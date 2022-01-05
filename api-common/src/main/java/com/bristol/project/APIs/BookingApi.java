package com.bristol.project.APIs;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//port:9300
@RequestMapping("/bookings")
public interface BookingApi {

    @PreAuthorize("hasAnyAuthority('admin','client')")
    @PostMapping("//{id}")
    Result<Appointment> create(@RequestBody Appointment appointment);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PutMapping("/completion/{id}")
    Result<Integer> completeAppointmentById(@PathVariable(value = "id") Long id);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PutMapping("/cancellation/{id}")
    Result<Integer> cancelAppointmentById(@PathVariable(value = "id") Long id);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PutMapping("/deletion/{id}")
    Result<Integer> deleteAppointmentById(@PathVariable(value = "id") Long id);


        //  @PreAuthorize("hasAnyAuthority('admin','barber','client')")
 //  @DeleteMapping("/{id}")
 //  Result<Appointment> deleteAppointmentById(@PathVariable("id") String id);
//
    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @GetMapping("/{username}")
    Result<Appointment> getAllCurrentAppointment(@PathVariable("username") String id);
//
 //   //@PreAuthorize("hasAuthority('admin')")
 //   @GetMapping
 //   Result<List<Appointment>> getAllAppointment();


}