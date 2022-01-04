package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    private Long id;
    private String username;
    private String shopName;
    private String ratePoints;
    private String locationDescription;
    private String serviceForGender;
    private String[] serviceTypes;
    private String phoneNumber;
    private String photo;
}
