package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

        private Long id;
        private String appointmentTime;
        private Timestamp bookingTime;
        private String clientUsername;
        private String barberShopName;
        private String serviceName;
        boolean isDeleted;
        boolean isCanceled;
        boolean isDone;
}

