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
