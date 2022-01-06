package com.bristol.project.dao;

import com.bristol.project.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BookingDao {

        int create(Appointment appointment);

        int completeAppointmentById(Long id);

        int cancelAppointmentById(Long id);

        int deleteAppointmentById(Long id);

        int destroyAppointmentById(Long id);

        Appointment getAppointmentById(Long id);

        List<Appointment> getAllAppointmentByUsername(String username);

        List<Appointment> getAllUserAppointmentIdByUsername(String username);
}
