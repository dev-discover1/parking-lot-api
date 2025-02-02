# Parking Lot System API
A Spring Boot-based Parking Lot Management System that handles vehicle entry, exit logs, user authentication, and dashboard insights like total users served and revenue calculation.

## Features
- Admin authentication
- Vehicle entry and exit management
- Dashboard for analytics
- Slots Management

## Requirements
- Java: 17+
- Maven: 3.8 or higher
- Database: MySQL

## Build & Run the Project
Ensure you have Java 17 and Maven installed.
```shell
mvn --version
mvn clean install  
mvn spring-boot:run  

```

## Folder Structure
```html
parking-lot-system
 ├── src
 │   ├── main
 │   │   ├── java/com/example/parking_lot
 │   │   │   ├── controller
 │   │   │   ├── model
 │   │   │   ├── repository
 │   │   │   ├── service
 │   │   │   └── security
 │   │   ├── resources
 │   │   │   └── application.properties
 ├── pom.xml
 └── README.md

```
