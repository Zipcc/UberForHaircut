package com.bristol.project.dao;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BookingDao {

        int create(Appointment appointment);

        int deleteAppointmentById(String id);

        Appointment getAppointmentById(String id);

        List<Appointment> getAllAppointment();
}
