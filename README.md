# SpringBootGateway

Basic Spring Boot Gateway

It'll be used as a single endpoint for VueJS application then it'll determine the right Eureka service depending on the url.
Then it'll forward the request to a corresponding host registered on the same eureka server.

In the application.yml just replace eureka server url by yours and change/add routes to match your microservices.
Here's a little schema of the current configuration 

![SpringBootGateway](https://github.com/user-attachments/assets/805d92ec-20b6-45e9-bbf7-8226f49d5fd4)
