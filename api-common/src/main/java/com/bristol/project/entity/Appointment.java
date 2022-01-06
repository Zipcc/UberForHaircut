package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

        private Long appointmentId;
        private String appointmentTime;
        private Timestamp bookingTime;
        private String clientUsername;
        private String barberUsername;
        private String barberShopName;
        private String serviceName;
        private String serviceDescription;
        boolean isCanceled;
        boolean isDone;
}

