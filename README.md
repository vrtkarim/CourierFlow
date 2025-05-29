# CourierFlow REST API

CourierFlow is a Spring Boot application that provides REST API endpoints for courier and delivery service management. It includes user authentication, route planning, and profile management features.

## Overview

CourierFlow is designed to help delivery personnel manage their routes and deliveries efficiently. The application uses GraphHopper for route planning and provides secure authentication using JWT.

## Features

- User authentication (sign up, sign in)
- Profile management
- Route planning with GraphHopper
- Vehicle type support
- Secure API endpoints with JWT authentication

## API Endpoints

### Authentication

#### Sign Up
- **URL**: `/signup`
- **Method**: `POST`
- **Parameters**:
  - `name`: User's full name
  - `password`: User's password
  - `email`: User's email address
  - `vehicule`: Type of vehicle used for deliveries
  - `image`: Profile image (multipart file)
- **Response**: JWT token for authentication

#### Sign In
- **URL**: `/signin`
- **Method**: `POST`
- **Body**:
  ```json
  {
    "email": "user@example.com",
    "password": "userpassword"
  }
  ```
- **Response**: User information including token, email, name, vehicle type, and profile image

### Profile Management

#### Edit Profile
- **URL**: `/editprofile`
- **Method**: `POST`
- **Authentication**: Required
- **Response**: Confirmation message

### Routing

#### Generate Route
- **URL**: `/route`
- **Method**: `POST`
- **Authentication**: Required
- **Body**:
  ```json
  {
    "source": {
      "latitude": 34.0522,
      "longitude": -118.2437
    },
    "destinations": [
      {
        "latitude": 34.0624,
        "longitude": -118.3008
      },
      {
        "latitude": 34.0753,
        "longitude": -118.2396
      }
    ]
  }
  ```
- **Response**: GPX file containing the route information (returned as attachment with filename "test.gpx")

### Public Endpoints

#### Public Test
- **URL**: `/public`
- **Method**: `GET`
- **Authentication**: Not required
- **Response**: Public message

#### Authenticated Test
- **URL**: `/authenticated`
- **Method**: `GET`
- **Authentication**: Required
- **Response**: Authenticated message

## Security

The API uses JWT (JSON Web Token) for authentication. After a successful login, the API returns a token that should be included in the Authorization header for subsequent requests to protected endpoints:

```
Authorization: Bearer {token}
```

## Technical Details

- Built with Spring Boot 3.5.0
- Java 17
- JWT for authentication
- GraphHopper for route planning and navigation
- JPA for database operations

## Installation and Setup

1. Clone the repository
2. Ensure you have Java 17 installed
3. Configure the database connection in `application.properties`
4. Make sure the required routing data file (`morocco-latest.osm.pbf`) should be in the resource folder
   to download any map check : https://download.geofabrik.de/
6. Run the application using Maven:
   ```
   mvn spring:boot run
   ```

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Boot Starter Data JPA
- GraphHopper
- JJWT (Java JWT)
- Lombok

## License

MIT License

## Contact

Email: elyaagoubi.karim03@gmail.com
