# Spring Boot Application

## Requirements

1. Java 17
3. Maven

## Setup

1. Clone the repository
2. Update `application.properties` with your database details
3. Build the application: `mvn clean install`
4. Run the application: `java -jar target/demo-0.0.1-SNAPSHOT.jar`

## APIs

### Authentication

- **Login:** `POST /token/get`
    - Request Body: `{ "username": "admin", "password": "admin123" }`
    - Response: `{ "token": "JWT token" }`

### Admin

- **Create Customer:**
- **Get All Customers:**
- **Delete Customer:**
- **Update Self (name, address, password):**

### Customer

- **Get My Details:**
- **Update My Details (name, address, password):**

## Postman Collection

- Import the Postman collection from `Onato.postman_collection.json`
