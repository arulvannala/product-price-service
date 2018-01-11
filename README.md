product-price-service
======================
This is the sample REST Spring boot microservice application written to run in the Pivotal Cloud Foundry. This application uses the Spring Cloud for different services required in microservice based architecture. This application connects to the database and returns the basic product price information. This application connects to Spring Config Server to get the product promotion code.

This application uses following features / services in the Pivotal Cloud Foundry:

### Spring Cloud Connectors 
This application uses Spring Cloud Connectors to discover binded services and connectivity to the MySql or RabbitMQ when services are binded in Cloud Foundry. 

### Database 
This application uses the in-memory h2 database if MySql service is not binded and uses the MySql if service is binded in Cloud Foundry.

### Service Discovery 
This application uses Service Discovery service of Pivotal Cloud Foundry which internally uses the Netflix Eureka and provides the rich features for example service registration method (registrationMethod) of direct which registers the container’s IP address in the service registry instead of public route for direct service to service (container to container) communication. This feature is handy for the internal service communication instead of service call go out to the public internet and back to the service. 

### Distributed Tracing
This application uses the Spring Cloud Sleuth to enable the Zipkin distributed tracing. 

### Container to Container Networking
For service to service call, this application uses the direct container to container communication for the service calls with the Cloud Foundry. This communication is useful for service to service call inside Cloud Foundry instead of call going out through internet. 

### Config Server
This application uses the Config Server service of Pivotal Cloud Foundry which internally uses the Spring Cloud Config. It gets the `productprice.promocode` setting from the settings in the ostoreconfig git repository.

### Spring Cloud Bus
This application uses the Spring Cloud Bus to refresh the service settings in all service instances instead of refreshing each individual service instance inside the Cloud Foundry.

## Running application inside Pivotal Cloud Foundry
### Service Binding
Service binding is defined in the manifest.yml file. Create the following services inside the Cloud Foundry with the name provided as below:

* `ClearDb` service name: `ostore-db`
* `Service Registry` service name: `ostore-service-registry`
* `Config Server` service name: `ostore-config-server`. Create the service pointing to the ostoreconfig git repository. Run following command to create service: `cf create-service -c '{ "git": { "uri": "https://github.com/mirfanmcs/ostoreconfig", "label": "master"  } }' p-config-server standard ostore-config-server`
* `CloudAMQP` service name: `ostore-rabbitmq`


### Route
Service doesn’t have the public route defined as service is intended to be called by the API Gateway. To create the route, enable the route in the manifest.yml file or in the push command. 

### Refresh Config 
To change the setting `productprice.promocode`, do the following steps:
* Update the setting value in the `https://github.com/mirfanmcs/ostoreconfig/blob/master/product-price-service.yml` git repo and commit the changes.
* Enable the public route of product price service. Enable either in the manifest.yml file or in the push command.
* Hit the following URL to refresh all the service instances at the same time: `http://product-price-service.cfapps.io/bus/refresh`.

### Push the application
After creating the services push the application using the `cf push` command. 

