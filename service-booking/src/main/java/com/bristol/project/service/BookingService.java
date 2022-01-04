package com.bristol.project.service;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import java.util.List;

public interface BookingService {

    Result<Appointment> create(Appointment appointment);

    //Result<Appointment> deleteAppointmentById(String id);

    //Result<Appointment> getAppointmentById(String id);

    //Result<List<Appointment>> getAllAppointment();
}
