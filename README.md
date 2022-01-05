# SpringCloudExample
This has 3 microservices: UserService, Product and PlaceOrder.
All of them are registered in Eureka(DiscoveryService) and can be accessed through spring cloud gateway(GatewayService).
UserService and Product microservices have the basic CRUD endpoints.
Placeorder Microservice provides endpoints for placing a new order, getting details of the registered users and product details for a list of products sent by the user.
It uses both feign client and rest template to implement this.
It publishes the order details in a queue using the rabbitmq if the order is placed successfully.
