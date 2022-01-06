package com.bristol.project.APIs;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//port:9300
@RequestMapping("/bookings")
public interface BookingApi {

    @PreAuthorize("hasAnyAuthority('admin','client')")
    @PostMapping
    Result<Appointment> create(@RequestBody Appointment appointment);

    @PreAuthorize("hasAnyAuthority('admin')")
    @DeleteMapping("/{id}")
    Result<Integer> destroyAppointmentById(@PathVariable(value = "id") Long id);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @DeleteMapping("/deletion/{id}")
    Result<Integer> deleteAppointmentById(@PathVariable(value = "id") Long id);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PutMapping("/completion/{id}")
    Result<Integer> completeAppointmentById(@PathVariable(value = "id") Long id);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @PutMapping("/cancellation/{id}")
    Result<Integer> cancelAppointmentById(@PathVariable(value = "id") Long id);

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/{username}")
    Result<List<Appointment>> getAllAppointmentByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @GetMapping("/me")
    Result<List<Appointment>> getAllCurrentAppointment();
}