package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

        private Long id;
        private String date;
        private String clientUserName;
        private String barberShopName;
        private String serviceType;
        private double cost;
        private Date bookingTime;
}

