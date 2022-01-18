# UberForHaircut-Backend


## The project is depolyed on the cloud server  
the application basic entry IP:Port is http://47.243.165.93:18080, but do not straight click into the website, the frontend is not developed here. 

Currently this application does not return visual pages, as is now developed as the IOS application backend, the return value are all in JSON style for the frontend to read.

### Try without frontend
    
#### Please use postman or similar applications to send requests to the server as to try the backend independently from the frontend.

----

## 1. [User service](./UserServiceReadMe.md)

[User Service](./service-user)  deals with user information, the user includes the client and the barber.

Users could register an account, updated their profiles and query their profiles.

More details please click the service title.

## 2. [Shop service](./ShopServiceReadMe.md)  

[Shop Service](./service-shop)  deals with barber shop information, including the barber services provided by the shop.

Users(barbers) could register a barber shop, updated their shop profiles, including adding, updating, deleting barber services and query information corresponding to the shop.

More details please click the service title.

## 3. [Booking service](./BookingServiceReadMe.md)  

[Booking Service](./service-booking)  deals with booking functionality.

Users(client) could book an appointment with the barber, the barber could set the appointment status to be done if the service is done. The client and barber could both cancel the appointment, and they could 'delete' their own appointment displayed on their application(their account).

More details please click the service title.

## 4. Uaa service

[Uaa Service](./service-uaa) deals with authentication and authorization.

This is the center of authentication and authorization, it gives out RSA encrypted JWT access tokens to accounts that are logged in, other services are assigned with related RSA public key, and they identify the authorizations of requesting account by decrypting the JWT access token and extracting account detail, such as roles, included in the JWT payload. 

Meanwhile, single sign on is achieved because other services no longer need to ask the user to log in, as long as the access token is not expried and valid(not modified by third party), the account is confirmed to be authenticated.

## 5. [Gateway](./api-gateway-common)

The gateway are working layer by layer, the outmost entry is the [center gateway](./api-gateway-common/api-gateway-center). It dispatches requests to other three gateways that in charges of their own services.


