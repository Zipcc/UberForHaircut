# UberForHaircut



## The project is depolyed on the cloud server  
the application entry IP:Port is http://47.243.165.93:18080 

### Try it !
    
#### Please use postman or similar applications to send requests to the server as to try the backend independently from the frontend.

----

## 1. [User service](./UserServiceReadMe.md)

This Service deals with user information, the user includes the client and the barber.

Users could register an account, updated their profiles and query their profiles.

## 2. [Shop service](./ShopServiceReadMe.md)  

This Service deals with barber shop information, including the barber services provided by the shop.

Users(barbers) could register a barber shop, updated their shop profiles, including adding, updating, deleting barber services and query information corresponding to the shop.

## 3. [Booking service](./BookingServiceReadMe.md)  

This Service deals with booking functionality.

Users(client) could book an appointment with the barber, the barber could set the appointment status to be done if the service is done. The client and barber could both cancel the appointment, and they could 'delete' their own appointment displayed on their application(their account).
