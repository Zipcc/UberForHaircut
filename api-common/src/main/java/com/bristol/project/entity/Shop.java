package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    private Long shopId;
    private String username;
    private String shopName;
    private String locationDescription;
    private String serviceForGender;
    private double ratePoints;
    private String phoneNumber;
    private List<ShopServ> shopServs;
}
