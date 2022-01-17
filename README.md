# UberForHaircut



## The project is depolyed on the cloud server  
the application entry IP:Port is http://47.243.165.93:18080 

## Try it yourself!
### Please use postman or similar applications to send requests to the server as to try the backend independently from the frontend.

  
----

# User service
## 1. Register as an application user
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

## 2. Login
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
> 2. Valid time of the access token is 15 days.

----

## 3. Update your profile
1. Request method: **PUT**  
2. Request URL: http://47.243.165.93:18080/ios/users/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoxLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RjbGllbnQiLCJpZCI6MzIsImV4cCI6MTY0MzcyMTIyNywiYXV0aG9yaXRpZXMiOlsiY2xpZW50Il0sImp0aSI6IjI1YjQ5YjUxLTI0ZDgtNGI4ZC1hZmVlLThmMWU2YzcwZTgwMyIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0Y2xpZW50In0.EUaOjDXc1X437QJx5Bo43CaOScmufPGwc3_ZY1ZuKvx-13c1d2UAinPyJJIfKoBt35ShBrqqu95Cp0dJdR7qhNbImVRwDeOlozIVCckqqobXgaYMUEg2jsBhXygiFFpOIGCGbTbULG5LNX_DK4JC8pXjro2v5yfdRUy1KCKEhEEoln4WaYdP3JlIGduNou5s9SH6vtRUdijCwLnPi0y6xHtANcZz8zpU6IaCZEFNtgiWYP9hiYMVulHlX6Bw2Ne08Eh99Gg0QUJTHS96cmAYzciYWhx5atwlIsx-FERrTPoP2PiVMZk0Uf1QcLF0524pxkWpEmzZBxSR6sbhoVwMRw`
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

## 4. Query your profile
1. Request method: **GET**  
2. Request URL: http://47.243.165.93:18080/ios/users/me?Authorization= ***${The access token}***
3. {The access token} is like 
`eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoxLCJzY29wZSI6WyJpb3MiXSwibmFtZSI6InRlc3RjbGllbnQiLCJpZCI6MzIsImV4cCI6MTY0MzcyMTIyNywiYXV0aG9yaXRpZXMiOlsiY2xpZW50Il0sImp0aSI6IjI1YjQ5YjUxLTI0ZDgtNGI4ZC1hZmVlLThmMWU2YzcwZTgwMyIsImNsaWVudF9pZCI6ImppYW5jaGVuY2xpZW50IiwidXNlcm5hbWUiOiJ0ZXN0Y2xpZW50In0.EUaOjDXc1X437QJx5Bo43CaOScmufPGwc3_ZY1ZuKvx-13c1d2UAinPyJJIfKoBt35ShBrqqu95Cp0dJdR7qhNbImVRwDeOlozIVCckqqobXgaYMUEg2jsBhXygiFFpOIGCGbTbULG5LNX_DK4JC8pXjro2v5yfdRUy1KCKEhEEoln4WaYdP3JlIGduNou5s9SH6vtRUdijCwLnPi0y6xHtANcZz8zpU6IaCZEFNtgiWYP9hiYMVulHlX6Bw2Ne08Eh99Gg0QUJTHS96cmAYzciYWhx5atwlIsx-FERrTPoP2PiVMZk0Uf1QcLF0524pxkWpEmzZBxSR6sbhoVwMRw`

> #### NOTE:
> 1. The server identifies the current user detail by extracting the user information which is integreted inside the access token, adding other user's username into the request body does not make effect.

### The response body should be like:

----

