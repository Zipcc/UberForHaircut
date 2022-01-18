# 2. Shop service  
    
- ## Register your shop
1. Request method: **POST**  
2. Request URL: http://47.243.165.93:18080/ios/shops?Authorization= ***${The access token}***
3. {The access token} of barber is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`
4. Request Body should contain a [Shop](/api-common/src/main/java/com/bristol/project/entity/Shop.java) object, containing fields like as follows

```
{
    "shopName":"testShop",
    "locationDescription": "testLocation",
    "serviceForGender": "testGender",
    "phoneNumber": "555555555555"
}
```

> #### NOTE:
> 1. The current user must be of role barber, and the registered shop only belongs to the current barber.
> 2. The *shopName* is required, should not be empty.
> 3. The *shopName* is unique, should be different from existing ones.


### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149908034-dc2f74c1-4474-4a7c-93ec-2784a14e4791.png)
----

- ## Register your barber services
1. Request method: **POST**  
2. Request URL: http://47.243.165.93:18080/ios/shops/services?Authorization= ***${The access token}***
3. {The access token} of barber is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`
4. Request Body should contain a barber service: [ShopServ](/api-common/src/main/java/com/bristol/project/entity/ShopServ.java) object, containing fields like as follows

```
{
    "serviceName": "testservice1",
    "description": "testservice1description1"
}
```

> #### NOTE:
> 1. The current user must be of role barber with an existing shop, and the registered barber service only belongs to the current barber's shop.
> 2. The *serviceName* is required, should not be empty.
> 3. One shop could add multiple shop services one by one. 

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149819261-d8d08cb4-2f92-4edb-902d-335e1c4cb57a.png)
![image](https://user-images.githubusercontent.com/45266501/149819355-cb007265-1c55-497f-94c0-524f2e1dac7a.png)

----

- ## Delete your barber services
1. Request method: **DELETE**  
2. Request URL: http://47.243.165.93:18080/ios/shops/services/{serviceId}?Authorization= ***${The access token}***
3. {The access token} of barber is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`
4. Request path variable should contain a {serviceId} of [ShopServ](/api-common/src/main/java/com/bristol/project/entity/ShopServ.java) object.

> #### NOTE:
> 1. The current user must be of role barber with an existing shop, and the deleted barber service should be existing.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149790883-4b1fb9e9-570f-41ef-9067-4fdffc1a647d.png)

----

- ## Update your shop
1. Request method: **PUT**  
2. Request URL: http://47.243.165.93:18080/ios/shops/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`
4. Request Body should contain a [Shop](/api-common/src/main/java/com/bristol/project/entity/Shop.java) object, containing fields like as follows

```
{
    "shopName":"testShop_update",
    "locationDescription": "testLocation_update",
    "serviceForGender": "testGender_update",
    "phoneNumber": "testPhone_update"
}
```

> #### NOTE:
> 1. The current user must be of role barber, and the updated shop only belongs to the current barber.
> 2. The *shopName* is unique, should be different from existing ones.


### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149793046-ed4f0b4a-e9f9-4662-802b-527134f065f0.png)

----

- ## Update your barber services
1. Request method: **PUT**  
2. Request URL: http://47.243.165.93:18080/ios/shops/services/{serviceId}?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`
4. Request path variable should contain a {serviceId} of [ShopServ](/api-common/src/main/java/com/bristol/project/entity/ShopServ.java) object.

```
{
    "serviceName": "testservice_update",
    "description": "testservicedescription_update"
}
```

> #### NOTE:
> 1. The current user must be of role barber with an existing shop, and the updated barber service should be existing inside the barber's own shop.


### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149804930-d172418c-c9d2-4221-a32d-1860784a4b48.png)

----

- ## Query your shop 
1. Request method: **GET**  
2. Request URL: http://47.243.165.93:18080/ios/shops/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`

> #### NOTE:
> 1. The current user must be of role barber with an existing shop.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149910656-63f4513d-44e0-45a1-be02-0b7f54148260.png)

----

- ## Explore 10 shops 
1. Request method: **GET**  
2. Request URL: http://47.243.165.93:18080/ios/shops?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`

> #### NOTE:
> 1. The current user could be any role logged in and request with a valid access token.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149820783-e3e9022e-ae57-4efa-8b63-baa0420815e9.png)

> #### NOTE:
> 1. The response contains an array of shops which are the most 10 rated shops in the application, ordered by the ratepoints.
