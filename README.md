# UberForHaircut



## The project is depolyed on the cloud server  
the application entry IP:Port is http://47.243.165.93:18080 

## Try it yourself!
### Please use postman or similar applications to send requests to the server as to try the backend independently from the frontend.

  
----

# 1. User service  
    
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
> 2. *username* should be different from existing usernames, and should not be 'me' as is reserved for the URL path.
> 3. The *role* is required, 1 means the client, 2 means the barber.
> 4. Other fields are optional, could be edited later.

### The response body should be like the right hand side in the image:    
![image](https://user-images.githubusercontent.com/45266501/149774063-3125892f-e5d5-4dda-accd-8d979df2a0ab.png)

> #### NOTE:
> 1. The *password* is encrypted with BCrypt.

----

- ## Login
1. Request method: **POST**  
2. Request URL: http://47.243.165.93:18080/ios/uaa/login
3. Request Headers should contain *username* and *password*
![image](https://user-images.githubusercontent.com/45266501/149775057-975c5ef2-1922-434a-954d-ef63cff05da7.png)

> #### NOTE:
> 1. The username and password are required, should not be empty.
### The response body should be like:   
![image](https://user-images.githubusercontent.com/45266501/149775501-1e011388-d6df-4d19-b775-c7fabafb772a.png)

> #### NOTE:
> 1. The access token (Authenticaion JWT Token) in the responed body should be recorded and later requestes should all sent with the token.
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
        "password": "testclient_UpdateUpdate",
}
```

> #### NOTE:
> 1. The server identifies the current user detail by extracting the user information which is integreted inside the access token, adding other user's username into the request body does not make effect.
> 2. Only the *age*, *firseName*, *lastName*, *emailAddress*, *phoneNumber*, *password* could be updated.

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
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6ImJhcmJlciIsImlkIjoyNiwiZXhwIjoxNjQyNjg4MjQwLCJhdXRob3JpdGllcyI6WyJiYXJiZXIiXSwianRpIjoiNmE2MDU3NWItMTUwNy00YTkzLThmYmMtMjJiMmE4ZTNiNGIwIiwiY2xpZW50X2lkIjoiamlhbmNoZW5jbGllbnQiLCJ1c2VybmFtZSI6ImJhcmJlciJ9.UDVOkL5SG33KQLBCDHQtsi3RpwVYuLGzikplzJU3eKuovRqD1SBwWCoIi8AcWzP_khrNMHkv0LV4lDFEI2kjmy-xWthm0jmxrC0JvXnhMnYF-ZE96qEE_kXEJNFL12GwEH7Et2nzOCIvsY6pSv_PyPbhuJcuZ7j6qh_H-NQ_U9MToPhfDNRbWywGVIhAGugByHV0cAfXo-_0QCwxATQGQ54o4UgYzL0_XcK89uuV2P5lUiBpk7wXV4H2eNRikt7Pi3-WmHN80RVOZCQCUxsOYdAPM0H5pHCGyazImuYCXGfaUwC0dhihaklpZINwjcGRFyUOn29d4R3bysCVlBL9yg`
4. Request Body should contain a [Shop](/api-common/src/main/java/com/bristol/project/entity/Shop.java) object, containing fields like as follows

```
{
    "shopName":"testShop",
    "locationDescription": "testLocation",
    "serviceForGender": "testGender",
    "phoneNumber": "testPhone"
}
```

> #### NOTE:
> 1. The current *user* must be of role *barber*, and the registered shop only belongs to the current barber.
> 2. The *shopName* is required, should not be empty.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149788717-e6ea62ee-76bb-4dc4-ac3f-4e5ad4d5e4fd.png)

----

- ## Register your barber services
1. Request method: **POST**  
2. Request URL: http://47.243.165.93:18080/ios/shops/services?Authorization= ***${The access token}***
3. {The access token} of barber is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6ImJhcmJlciIsImlkIjoyNiwiZXhwIjoxNjQyNjg4MjQwLCJhdXRob3JpdGllcyI6WyJiYXJiZXIiXSwianRpIjoiNmE2MDU3NWItMTUwNy00YTkzLThmYmMtMjJiMmE4ZTNiNGIwIiwiY2xpZW50X2lkIjoiamlhbmNoZW5jbGllbnQiLCJ1c2VybmFtZSI6ImJhcmJlciJ9.UDVOkL5SG33KQLBCDHQtsi3RpwVYuLGzikplzJU3eKuovRqD1SBwWCoIi8AcWzP_khrNMHkv0LV4lDFEI2kjmy-xWthm0jmxrC0JvXnhMnYF-ZE96qEE_kXEJNFL12GwEH7Et2nzOCIvsY6pSv_PyPbhuJcuZ7j6qh_H-NQ_U9MToPhfDNRbWywGVIhAGugByHV0cAfXo-_0QCwxATQGQ54o4UgYzL0_XcK89uuV2P5lUiBpk7wXV4H2eNRikt7Pi3-WmHN80RVOZCQCUxsOYdAPM0H5pHCGyazImuYCXGfaUwC0dhihaklpZINwjcGRFyUOn29d4R3bysCVlBL9yg`
4. Request Body should contain a barber service: [ShopServ](/api-common/src/main/java/com/bristol/project/entity/ShopServ.java) object, containing fields like as follows

```
{
    "serviceName": "testservice1",
    "description": "testservice1description"
}
```

> #### NOTE:
> 1. The current *user* must be of role *barber* with an existing shop, and the registered barber service only belongs to the current barber's shop.
> 2. The *serviceName* is required, should not be empty.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149790209-ffbb2d48-3ffe-4aeb-a39f-1a872f85e2ad.png)

----

- ## Delete your barber services
1. Request method: **DELETE**  
2. Request URL: http://47.243.165.93:18080/ios/shops/services/{serviceId}?Authorization= ***${The access token}***
3. {The access token} of barber is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6ImJhcmJlciIsImlkIjoyNiwiZXhwIjoxNjQyNjg4MjQwLCJhdXRob3JpdGllcyI6WyJiYXJiZXIiXSwianRpIjoiNmE2MDU3NWItMTUwNy00YTkzLThmYmMtMjJiMmE4ZTNiNGIwIiwiY2xpZW50X2lkIjoiamlhbmNoZW5jbGllbnQiLCJ1c2VybmFtZSI6ImJhcmJlciJ9.UDVOkL5SG33KQLBCDHQtsi3RpwVYuLGzikplzJU3eKuovRqD1SBwWCoIi8AcWzP_khrNMHkv0LV4lDFEI2kjmy-xWthm0jmxrC0JvXnhMnYF-ZE96qEE_kXEJNFL12GwEH7Et2nzOCIvsY6pSv_PyPbhuJcuZ7j6qh_H-NQ_U9MToPhfDNRbWywGVIhAGugByHV0cAfXo-_0QCwxATQGQ54o4UgYzL0_XcK89uuV2P5lUiBpk7wXV4H2eNRikt7Pi3-WmHN80RVOZCQCUxsOYdAPM0H5pHCGyazImuYCXGfaUwC0dhihaklpZINwjcGRFyUOn29d4R3bysCVlBL9yg`
4. Request path variable should contain a {serviceId} of [ShopServ](/api-common/src/main/java/com/bristol/project/entity/ShopServ.java) object.

> #### NOTE:
> 1. The current *user* must be of role *barber* with an existing shop, and the deleted barber service should be existing.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149790883-4b1fb9e9-570f-41ef-9067-4fdffc1a647d.png)

----

- ## Update your shop
1. Request method: **PUT**  
2. Request URL: http://47.243.165.93:18080/ios/shops/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6ImJhcmJlciIsImlkIjoyNiwiZXhwIjoxNjQyNjg4MjQwLCJhdXRob3JpdGllcyI6WyJiYXJiZXIiXSwianRpIjoiNmE2MDU3NWItMTUwNy00YTkzLThmYmMtMjJiMmE4ZTNiNGIwIiwiY2xpZW50X2lkIjoiamlhbmNoZW5jbGllbnQiLCJ1c2VybmFtZSI6ImJhcmJlciJ9.UDVOkL5SG33KQLBCDHQtsi3RpwVYuLGzikplzJU3eKuovRqD1SBwWCoIi8AcWzP_khrNMHkv0LV4lDFEI2kjmy-xWthm0jmxrC0JvXnhMnYF-ZE96qEE_kXEJNFL12GwEH7Et2nzOCIvsY6pSv_PyPbhuJcuZ7j6qh_H-NQ_U9MToPhfDNRbWywGVIhAGugByHV0cAfXo-_0QCwxATQGQ54o4UgYzL0_XcK89uuV2P5lUiBpk7wXV4H2eNRikt7Pi3-WmHN80RVOZCQCUxsOYdAPM0H5pHCGyazImuYCXGfaUwC0dhihaklpZINwjcGRFyUOn29d4R3bysCVlBL9yg`
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
> 1. The current *user* must be of role *barber*, and the updated shop only belongs to the current barber.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149793046-ed4f0b4a-e9f9-4662-802b-527134f065f0.png)

----

- ## Update your barber services
1. Request method: **PUT**  
2. Request URL: http://47.243.165.93:18080/ios/shops/services/{serviceId}?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6ImJhcmJlciIsImlkIjoyNiwiZXhwIjoxNjQyNjg4MjQwLCJhdXRob3JpdGllcyI6WyJiYXJiZXIiXSwianRpIjoiNmE2MDU3NWItMTUwNy00YTkzLThmYmMtMjJiMmE4ZTNiNGIwIiwiY2xpZW50X2lkIjoiamlhbmNoZW5jbGllbnQiLCJ1c2VybmFtZSI6ImJhcmJlciJ9.UDVOkL5SG33KQLBCDHQtsi3RpwVYuLGzikplzJU3eKuovRqD1SBwWCoIi8AcWzP_khrNMHkv0LV4lDFEI2kjmy-xWthm0jmxrC0JvXnhMnYF-ZE96qEE_kXEJNFL12GwEH7Et2nzOCIvsY6pSv_PyPbhuJcuZ7j6qh_H-NQ_U9MToPhfDNRbWywGVIhAGugByHV0cAfXo-_0QCwxATQGQ54o4UgYzL0_XcK89uuV2P5lUiBpk7wXV4H2eNRikt7Pi3-WmHN80RVOZCQCUxsOYdAPM0H5pHCGyazImuYCXGfaUwC0dhihaklpZINwjcGRFyUOn29d4R3bysCVlBL9yg`
4. Request path variable should contain a {serviceId} of [ShopServ](/api-common/src/main/java/com/bristol/project/entity/ShopServ.java) object.

```
{
    "serviceName": "testservice_update",
    "description": "testservicedescription_update"
}
```

> #### NOTE:
> 1. The current *user* must be of role *barber* with an existing shop, and the updated barber service should be existing inside the shop.


### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149804930-d172418c-c9d2-4221-a32d-1860784a4b48.png)

----

- ## Query your shop 
1. Request method: **GET**  
2. Request URL: http://47.243.165.93:18080/ios/shops/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6ImJhcmJlciIsImlkIjoyNiwiZXhwIjoxNjQyNjg4MjQwLCJhdXRob3JpdGllcyI6WyJiYXJiZXIiXSwianRpIjoiNmE2MDU3NWItMTUwNy00YTkzLThmYmMtMjJiMmE4ZTNiNGIwIiwiY2xpZW50X2lkIjoiamlhbmNoZW5jbGllbnQiLCJ1c2VybmFtZSI6ImJhcmJlciJ9.UDVOkL5SG33KQLBCDHQtsi3RpwVYuLGzikplzJU3eKuovRqD1SBwWCoIi8AcWzP_khrNMHkv0LV4lDFEI2kjmy-xWthm0jmxrC0JvXnhMnYF-ZE96qEE_kXEJNFL12GwEH7Et2nzOCIvsY6pSv_PyPbhuJcuZ7j6qh_H-NQ_U9MToPhfDNRbWywGVIhAGugByHV0cAfXo-_0QCwxATQGQ54o4UgYzL0_XcK89uuV2P5lUiBpk7wXV4H2eNRikt7Pi3-WmHN80RVOZCQCUxsOYdAPM0H5pHCGyazImuYCXGfaUwC0dhihaklpZINwjcGRFyUOn29d4R3bysCVlBL9yg`

> #### NOTE:
> 1. The current *user* must be of role *barber* with an existing shop.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149805699-d6d4de81-dc1e-4173-b3e8-3a36b2a1e7ec.png)

----

- ## Explore 10 shops 
1. Request method: **GET**  
2. Request URL: http://47.243.165.93:18080/ios/shops?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoyLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6ImJhcmJlciIsImlkIjoyNiwiZXhwIjoxNjQyNjg4MjQwLCJhdXRob3JpdGllcyI6WyJiYXJiZXIiXSwianRpIjoiNmE2MDU3NWItMTUwNy00YTkzLThmYmMtMjJiMmE4ZTNiNGIwIiwiY2xpZW50X2lkIjoiamlhbmNoZW5jbGllbnQiLCJ1c2VybmFtZSI6ImJhcmJlciJ9.UDVOkL5SG33KQLBCDHQtsi3RpwVYuLGzikplzJU3eKuovRqD1SBwWCoIi8AcWzP_khrNMHkv0LV4lDFEI2kjmy-xWthm0jmxrC0JvXnhMnYF-ZE96qEE_kXEJNFL12GwEH7Et2nzOCIvsY6pSv_PyPbhuJcuZ7j6qh_H-NQ_U9MToPhfDNRbWywGVIhAGugByHV0cAfXo-_0QCwxATQGQ54o4UgYzL0_XcK89uuV2P5lUiBpk7wXV4H2eNRikt7Pi3-WmHN80RVOZCQCUxsOYdAPM0H5pHCGyazImuYCXGfaUwC0dhihaklpZINwjcGRFyUOn29d4R3bysCVlBL9yg`

> #### NOTE:
> 1. The current *user* could any role logged in.

### The response body should be like:
![image](https://user-images.githubusercontent.com/45266501/149806320-4d380a21-b1c8-4c78-a129-c537fecf9f77.png)

> #### NOTE:
> 1. The response contains an array of shops which are the most 10 rated shops in the application, ordered by the ratepoints.

