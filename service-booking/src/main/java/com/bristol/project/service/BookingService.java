package com.bristol.project.service;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;

import java.util.List;

public interface BookingService {



    enum UPDATE_TYPE { COMPLETE, CANCEL, DELETE }

    Result<Appointment> create(Appointment appointment);

    Result<Integer> destroyAppointmentById(Long appointmentId);

    Result<Integer> updateAppointmentById(Long appointmentId, UPDATE_TYPE update_type, String username);

    Result<List<Appointment>> getAllAppointmentByUsername(String username);

    Result<List<Appointment>> getAllCurrentAppointment(String username);

}
