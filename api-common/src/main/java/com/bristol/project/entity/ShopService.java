package com.bristol.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopService {

    private Long ShopServiceId;
    private Long shopId;
    private String serviceName;
    private String description;
}
