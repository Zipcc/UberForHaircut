# UberForHaircut



## The project is depolyed on the cloud server  
the application entry IP:Port is http://47.243.165.93:18080 

## Try it !
### Please use postman or similar applications to send requests to the server as to try the backend independently from the frontend.

----

# 1. [User service](./UserServiceReadMe.md)
    
- ## Register as an application user
1. Request method: **POST**  
2. Request URL: http://47.243.165.93:18080/ios/users
3. Request Body should contain an [User](/api-common/src/main/java/com/bristol/project/entity/User.java) object, like as follows

```
{
        "age": 25,
        "firstName": "testfirst",
        "lastName": "testlast",
        "emailAddress": "testemail@email.com",
        "phoneNumber": "100000000",
        "sex": "male",
        "username": "testclient",
        "password": "testclient",
        "role": 1
}
```

> #### NOTE:
> 1. The *username* and *password* are required, should not be empty.
> 2. *username* is unique, should be different from existing usernames, and should not be 'me' as is reserved for the URL path.
> 3. The *role* is required, 1 means the client, 2 means the barber.
> 4. The *phoneNumber* and *emailAddress* are unique, should be different from existing ones.
> 5. Other fields are optional, could be edited later.

### The response body should be like the right hand side in the image:    
![image](https://user-images.githubusercontent.com/45266501/149817219-f4453941-f73b-4ab3-a36c-d12277d3e43b.png)
![image](https://user-images.githubusercontent.com/45266501/149901875-1ca08b78-73e3-4a3a-a7b7-5d8f3ac81a72.png)

> #### NOTE:
> 1. The *password* is encrypted with BCrypt.

----

- ## Login
1. Request method: **POST**  
2. Request URL: http://47.243.165.93:18080/ios/uaa/login
3. Request Headers should contain *username* and *password*
![image](https://user-images.githubusercontent.com/45266501/149775057-975c5ef2-1922-434a-954d-ef63cff05da7.png)

> #### NOTE:
> 1. The *username* and *password* are required, should not be empty.
### The response body should be like:   
![image](https://user-images.githubusercontent.com/45266501/149775501-1e011388-d6df-4d19-b775-c7fabafb772a.png)

> #### NOTE:
> 1. The access token (Authenticaion JWT Token) in the responed body should be recorded and later requestes should all sent with the token. In real depolyment this work should be done by the frontend.
> 2. Valid time of the access token is 30 days.

----

- ## Update your profile
1. Request method: **PUT**  
2. Request URL: http://47.243.165.93:18080/ios/users/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoxLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RjbGllbnQiLCJpZCI6MzIsImV4cCI6MTY0MjY4ODE1NywiYXV0aG9yaXRpZXMiOlsiY2xpZW50Il0sImp0aSI6IjY3Y2YyZTU3LTY3NWYtNDllZi05ZmQ2LTdiYjY4MGE2NGZkOCIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0Y2xpZW50In0.hlrfks_eOJ6kzKvWmDrjODv7N65tdCzyX-OaUyXMoJw52foUI7iVWy6Zgbc_ygXeNYCqew5DNIY19_8K-cTGeAhBbVXoK7f7hjXB0g8sT7rAplnW9uMVYuccLFn69gbftvuE6J0NNmw9fqEBPqKrxOagIq50jgXtb07oAx7-XCSaTl4IbliCd0B_IDADz6j9KGCqO5DS5-4ih_CvGpi-3xou6NNXOaOzUxmRCUZxIzTcfntowFOtATLIpkc41YiOoBLOsJQwgHDzLNTKPWr6NQXeWDvGCxcnuKIpBEVc9YVqf5RMYB5xgQxS3dU05aoWOgJNO-ug3Jg0Y2yQni_Rnw`
4. Request Body should contain an [User](/api-common/src/main/java/com/bristol/project/entity/User.java) object, containing fields like as follows

```
{
        "age": 35,
        "firstName": "testfirst_UpdateUpdate",
        "lastName": "testlast_UpdateUpdate",
        "emailAddress": "testemail_UpdateUpdate@email.com",
        "phoneNumber": "100000000_UpdateUpdate",
        "password": "testclient_UpdateUpdate"
}
```

> #### NOTE:
> 1. The server identifies the current user detail by extracting the user information which is integreted inside the access token, adding other user's *username* into the request body does not make effect.
> 2. The *phoneNumber* and *emailAddress* are unique, should be different from existing ones.
> 3. Only the *age*, *firseName*, *lastName*, *emailAddress*, *phoneNumber*, *password* could be updated.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149779108-2f17fc58-021a-4cd9-88c2-8ee7b577d2da.png)

----

- ## Query your profile
1. Request method: **GET**  
2. Request URL: http://47.243.165.93:18080/ios/users/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoxLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RjbGllbnQiLCJpZCI6MzIsImV4cCI6MTY0MjY4ODE1NywiYXV0aG9yaXRpZXMiOlsiY2xpZW50Il0sImp0aSI6IjY3Y2YyZTU3LTY3NWYtNDllZi05ZmQ2LTdiYjY4MGE2NGZkOCIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0Y2xpZW50In0.hlrfks_eOJ6kzKvWmDrjODv7N65tdCzyX-OaUyXMoJw52foUI7iVWy6Zgbc_ygXeNYCqew5DNIY19_8K-cTGeAhBbVXoK7f7hjXB0g8sT7rAplnW9uMVYuccLFn69gbftvuE6J0NNmw9fqEBPqKrxOagIq50jgXtb07oAx7-XCSaTl4IbliCd0B_IDADz6j9KGCqO5DS5-4ih_CvGpi-3xou6NNXOaOzUxmRCUZxIzTcfntowFOtATLIpkc41YiOoBLOsJQwgHDzLNTKPWr6NQXeWDvGCxcnuKIpBEVc9YVqf5RMYB5xgQxS3dU05aoWOgJNO-ug3Jg0Y2yQni_Rnw`

> #### NOTE:
> 1. The server identifies the current user detail by extracting the user information which is integreted inside the access token.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149781106-72bd8a5b-a6c1-45de-81e8-102d3db81088.png)

----

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

# 3. Booking service  
    
- ## Book an appointment
1. Request method: **POST**  
2. Request URL: http://47.243.165.93:18080/ios/bookings?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoxLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RjbGllbnQiLCJpZCI6MzIsImV4cCI6MTY0MjY4ODE1NywiYXV0aG9yaXRpZXMiOlsiY2xpZW50Il0sImp0aSI6IjY3Y2YyZTU3LTY3NWYtNDllZi05ZmQ2LTdiYjY4MGE2NGZkOCIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0Y2xpZW50In0.hlrfks_eOJ6kzKvWmDrjODv7N65tdCzyX-OaUyXMoJw52foUI7iVWy6Zgbc_ygXeNYCqew5DNIY19_8K-cTGeAhBbVXoK7f7hjXB0g8sT7rAplnW9uMVYuccLFn69gbftvuE6J0NNmw9fqEBPqKrxOagIq50jgXtb07oAx7-XCSaTl4IbliCd0B_IDADz6j9KGCqO5DS5-4ih_CvGpi-3xou6NNXOaOzUxmRCUZxIzTcfntowFOtATLIpkc41YiOoBLOsJQwgHDzLNTKPWr6NQXeWDvGCxcnuKIpBEVc9YVqf5RMYB5xgQxS3dU05aoWOgJNO-ug3Jg0Y2yQni_Rnw`
4. Request Body should contain an [Appointment](/api-common/src/main/java/com/bristol/project/entity/Appointment.java) object, containing fields like as follows

```
{
        "clientUsername": "testclient",
        "barberUsername": "testbarber",
        "serviceName": "testservice1",
        "appointmentTime": "Tomorrow (2011.11.11) At 10am, maybe a bit later..."
}
```

> #### NOTE:
> 1. The current user must be of role client.
> 2. The *clientUsername*, *barberUsername*, *serviceName* and *appointmentTime* are required, should not be empty and should be existing in the application.
> 3. There is no need to add such fields into request body: *appointmentId*, *bookingTime* are generated automatically by the service, *barberShopName*,  *serviceDescription* are queried from other micro services and filled automatically by the service.
> 4. The appointment record would be stored into both appointment_main table and appointment_user table. While appointment_main stores the primary data and appointment_user only stores username and appointment id, indicating what should be displayed to the user, for the frontend to fetch and display, because sometimes the user would delete the record on their application, then the frontend would not fetch the deleted record from the backend.   
### The response body should be like :    
![image](https://user-images.githubusercontent.com/45266501/149889764-df876b26-1521-490d-92b3-0fa54a84f387.png)

----

- ## Delete an appointment
1. Request method: **DELETE**  
2. Request URL: http://47.243.165.93:18080/ios/bookings/deletion/{appointmentId}?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoxLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RjbGllbnQiLCJpZCI6MzIsImV4cCI6MTY0MjY4ODE1NywiYXV0aG9yaXRpZXMiOlsiY2xpZW50Il0sImp0aSI6IjY3Y2YyZTU3LTY3NWYtNDllZi05ZmQ2LTdiYjY4MGE2NGZkOCIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0Y2xpZW50In0.hlrfks_eOJ6kzKvWmDrjODv7N65tdCzyX-OaUyXMoJw52foUI7iVWy6Zgbc_ygXeNYCqew5DNIY19_8K-cTGeAhBbVXoK7f7hjXB0g8sT7rAplnW9uMVYuccLFn69gbftvuE6J0NNmw9fqEBPqKrxOagIq50jgXtb07oAx7-XCSaTl4IbliCd0B_IDADz6j9KGCqO5DS5-4ih_CvGpi-3xou6NNXOaOzUxmRCUZxIzTcfntowFOtATLIpkc41YiOoBLOsJQwgHDzLNTKPWr6NQXeWDvGCxcnuKIpBEVc9YVqf5RMYB5xgQxS3dU05aoWOgJNO-ug3Jg0Y2yQni_Rnw`
4. Request path variable should contain a {appointmentId} of [Appointment](/api-common/src/main/java/com/bristol/project/entity/Appointment.java) object.

> #### NOTE:
> 1. The current user could be any role logged in and request with a valid access token.
> 2. The appointment would be deleted from the user's application (no longer been displayed), which means the frontend would not fetch such appointment and show to the user, however, for later analysis, the appointment would still be stored in the appointment_main table untill the admin destroyed the record.
    
### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149895389-ca46b324-142f-4446-a601-28dd41a417af.png)

----

- ## Complete an appointment
1. Request method: **PUT**  
2. Request URL: http://47.243.165.93:18080/ios/bookings/completion/{appointmentId}?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`
4. Request path variable should contain a {appointmentId} of [Appointment](/api-common/src/main/java/com/bristol/project/entity/Appointment.java) object.

> #### NOTE:
> 1. The current user must be of role barber.
> 2. The appointment in the appointment_main table would be set completed, both the involved client and barber could see the synchronized status from frontend.
    
### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149896771-9bb7ccae-96a4-44b5-baf0-3f71443e81f5.png)

----

- ## Cancel an appointment
1. Request method: **PUT**  
2. Request URL: http://47.243.165.93:18080/ios/bookings/cancellation/{appointmentId}?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RiYXJiZXIiLCJpZCI6NDMsImV4cCI6MTY0Mjc1NTExMywiYXV0aG9yaXRpZXMiOlsiYmFyYmVyIl0sImp0aSI6IjFkOWJjODBjLWQ5NDAtNGE2Zi1hMTEyLTIyZTMxMDM2M2IxMiIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0YmFyYmVyIn0.iw3ZCIFgPTaWflrONUsTI4_IJ4A4EWhdFQrma390zEsHLGiSboBIptxQUZs92g26nr6BNzVnSWtpnY_d03l4agiFbGyJH_PLm8aVEHvCQK3DYOkLSQ5Zy4pRCi7iR3Vr40U5ECNWC3AXjImX4VR-YneYDx2wyPMwvIeZy-tkS7UgLRPVeajnLy74mmZ0iK9ZfsU4rulr94tbp5zCcSvPHNgo50UYkOdg78s4_Ha6bsX6Oo_1IawtkkWyX9utO-gbTHWx5NmSsXcR9UasxTw8TxBTCB-cMItJMocyV8ofd4ttvOIWljySGbNHbDPfbflstgH4-cBUmgbam6cqW--8dA`
4. Request path variable should contain a {appointmentId} of [Appointment](/api-common/src/main/java/com/bristol/project/entity/Appointment.java) object.

> #### NOTE:
> 1. The current user could be any role logged in and request with a valid access token.
> 2. The appointment in the appointment_main table would be set canceled, both the involved client and barber could see the synchronized status from frontend.
    
### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149897253-a7a309a3-f7db-4204-a859-a2e00be90bf7.png)

- ## Query your appointment 
1. Request method: **GET**  
2. Request URL: http://47.243.165.93:18080/ios/bookings/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoxLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RjbGllbnQiLCJpZCI6MzIsImV4cCI6MTY0MjY4ODE1NywiYXV0aG9yaXRpZXMiOlsiY2xpZW50Il0sImp0aSI6IjY3Y2YyZTU3LTY3NWYtNDllZi05ZmQ2LTdiYjY4MGE2NGZkOCIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0Y2xpZW50In0.hlrfks_eOJ6kzKvWmDrjODv7N65tdCzyX-OaUyXMoJw52foUI7iVWy6Zgbc_ygXeNYCqew5DNIY19_8K-cTGeAhBbVXoK7f7hjXB0g8sT7rAplnW9uMVYuccLFn69gbftvuE6J0NNmw9fqEBPqKrxOagIq50jgXtb07oAx7-XCSaTl4IbliCd0B_IDADz6j9KGCqO5DS5-4ih_CvGpi-3xou6NNXOaOzUxmRCUZxIzTcfntowFOtATLIpkc41YiOoBLOsJQwgHDzLNTKPWr6NQXeWDvGCxcnuKIpBEVc9YVqf5RMYB5xgQxS3dU05aoWOgJNO-ug3Jg0Y2yQni_Rnw`

> #### NOTE:
> 1. The server identifies the current user detail by extracting the user information which is integreted inside the access token.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149898537-984a46b1-86e7-4b67-9e4a-465aaf57123a.png)
