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
