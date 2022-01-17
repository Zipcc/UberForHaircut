# UberForHaircut



## The project is depolyed on the cloud server  
the application entry Ip:Port is http://47.243.165.93:18080 

## Try it yourself!
### Please use postman or similar applications to send requests to the server as to try the backend independently from the frontend.

  
----

## 1. Register as an application user
1. Request method: **POST**  
2. Request URL: http://47.243.165.93:18080/ios/users
3. Request Body should contain an User object, like as follows


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

### NOTE:
1. The username and password are required, should not be empty.
2. The role is required, 1 means the client, 2 means the barber.
3. Other fields are optional, could be edited later.
