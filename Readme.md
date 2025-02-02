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

## Application Routes

### TEST `/`
- `GET` `/`
- `GET` `/test-db`

### ADMIN AUTH `/auth`

- `GET` `auth/admins`

- `POST` `auth/register`
```json
{
  "username": "adminUsername",
  "password": "adminPassword"
}
```
- `POST` `auth/login`
```json
{
  "username": "adminUsername",
  "password": "adminPassword"
}
```

### Dashboard `/dashboard`

- `GET` `/dashboard/logs/exits`
- `GET` `/dashboard/users/served?date={date}`
- `GET` `/dashboard/users/served/all`
- `GET` `/dashboard/revenue?startDate={startDate}&endDate={endDate}`
- `GET` `/dashboard/revenue/all`
- `DATE FORMAT` - `{YYYY-MM-DD}`

### Slot `/slots`

- `GET` `/slots`
- `POST` `/slots/add?slotNumber={slotNumber}`
- `POST` `/slots/occupy?slotNumber={slotNumber}`
- `POST` `/slots/free?slotNumber={slotNumber}`
- `POST` `/slots/delete?slotNumber={slotNumber}`
- `slotNumber` - `For ex. S1`

### Vehicle `/vehicles`
- `GET` `/vehicles/{vehicleNumber}`
- `GET` `/vehicles`
- `POST` `/vehicles/entry?vehicleNumber={vehicleNumber}`
- `POST` `/vehicles/exit?vehicleNumber={vehicleNumber}`
- `vehicleNumber` - `For ex. MH12AB1234`

### Security (Authenticated Routes)
- `/dashboard/**`
- `/slots/**`
- `/auth/admins`
- The above routes requires authentication pass the Token in Header format.
- 