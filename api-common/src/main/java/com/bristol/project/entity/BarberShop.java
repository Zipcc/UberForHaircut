package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarberShop {

        private Long id;
        private User barber;
        private String shopName;
        private String ratePoints;
        private String locationDescription;
        private String serviceForGender;
        private String[] serviceTypes;
        private String phoneNumber;
        private String photo;
}
