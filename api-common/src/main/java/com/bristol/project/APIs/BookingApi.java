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
    @DeleteMapping("/{appointmentId}")
    Result<Integer> destroyAppointmentById(@PathVariable(value = "appointmentId") Long appointmentId);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @DeleteMapping("/deletion/{appointmentId}")
    Result<Integer> deleteAppointmentById(@PathVariable(value = "appointmentId") Long appointmentId);

    @PreAuthorize("hasAnyAuthority('admin','barber')")
    @PutMapping("/completion/{appointmentId}")
    Result<Integer> completeAppointmentById(@PathVariable(value = "appointmentId") Long appointmentId);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @PutMapping("/cancellation/{appointmentId}")
    Result<Integer> cancelAppointmentById(@PathVariable(value = "appointmentId") Long appointmentId);

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/{username}")
    Result<List<Appointment>> getAllAppointmentByUsername(@PathVariable("username") String username);

    @PreAuthorize("hasAnyAuthority('admin','barber','client')")
    @GetMapping("/me")
    Result<List<Appointment>> getAllCurrentAppointment();
}