# GARAGE Rest Controller 
**Default Port:** 8080  
**Default Context Path:** /garage

## DEVELOPMENT
### REQUIREMENTS
```
OpenJDK 8
```


### Idea Configuration
```
Main class: com.dogankamer.Application
JRE: 1.8
```

## BUILD
```
./mvnw clean package
```

## HOW TO RUN
### MANUAL
```
java -jar garage-1.0-SNAPSHOT.jar
```


## Swagger
http://localhost:8080/garage/swagger-ui.html

## Authorization

Tokens are configured in application.yml file

**Web Headers:**
```
Authorization: Bearer 123456
```

## Postman call collection
sample postman collection is found under project main directory ```garage.postman_collection.json```