package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    private Long id;
    private String username;
    private String shopName;
    private String locationDescription;
    private String serviceForGender;
    private String ratePoints;
    private List<ShopService> shopServices;
}
