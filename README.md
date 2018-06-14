# camel-routing-aggregation
This project shows routing aggregation with springboot, Jersey API and Apache Camel.Messages will be posted from API controller and writes them to standard output.

# Camel routes
All the camel configurations are located under /src/main/java/com/example/camel/config/
The Camel route is located in the CamelRouters.java class. In this class the route starts from a tenant object, that triggers every 1000 seconds and stream endpoint which writes to standard output.

# Prerequisites
jdk Installation
IDE - intellij IDEA
Gradle installation


# Built With
Springboot - The web framework used
Gradle - Dependency Management
Jersey API - Used to generate REST API calls
slfj -  Used for logging 

# How to run
You can run this example using
gradle bootRun 

# To get health check
To show a summary of spring boot health check
GET: http://localhost:8080/tenant/

# More information
You can find more information about Apache Camel at the website: http://camel.apache.org/
