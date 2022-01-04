package com.bristol.project.dao;

import com.bristol.project.entity.Appointment;
import com.bristol.project.entity.Result;
import com.bristol.project.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface BookingDao {

        int create(Appointment appointment);

        int completeAppointmentById(Long id);

        int cancelAppointmentById(Long id);

        int deleteAppointmentById(Long id);

        Appointment getAppointmentById(Long id);

        List<Appointment> getAllAppointment();
}
