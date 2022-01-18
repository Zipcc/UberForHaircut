package com.bristol.project.dao;

import com.bristol.project.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BookingDao {

        int create(Appointment appointment);

        int completeAppointmentById(Long appointmentId);

        int cancelAppointmentById(Long appointmentId);

        int deleteAppointmentById(Long appointmentId, String username);

        int destroyAppointmentById(Long appointmentId);

        Appointment getAppointmentById(Long appointmentId);

        List<Appointment> getAllAppointmentByUsername(String username);

        List<Appointment> getAllUserAppointmentIdByUsername(String username);
}
