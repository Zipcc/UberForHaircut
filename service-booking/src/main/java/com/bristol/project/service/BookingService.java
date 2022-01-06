package com.bristol.project.service;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BookingService {



    public static enum UPDATE_TYPE { COMPLETE, CANCEL, DELETE };

    Result<Appointment> create(Appointment appointment);

    Result<Integer> destroyAppointmentById(Long id);

    Result<Integer> updateAppointmentById(Long id, UPDATE_TYPE update_type);

    Result<List<Appointment>> getAllAppointmentByUsername(String username);

    Result<List<Appointment>> getAllCurrentAppointment(String username);

    //Result<Appointment> getAppointmentById(String id);

    //Result<List<Appointment>> getAllAppointment();
}
