# Java Project: Book Rental System

This project is a Book Rental System built using Spring Boot and Spring Security for secure user authentication and authorization.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Technologies Used](#technologies-used)

## Overview
The Book Rental System allows users to browse, rent, and manage books. It includes authentication and authorization mechanisms using Spring Security and a RESTful API.

## Features
- User registration and login with Spring Security authentication.
- Secure access to book rental functionalities.
- Responsive front-end integration with React.
- API endpoints for managing books, users, and rentals.
- Error handling with custom responses.

## Architecture
The application follows a typical three-tier architecture:
- **Frontend:** Built with React for a user-friendly interface.
- **Backend:** Spring Boot application managing business logic and API endpoints.
- **Database:** Uses a relational database **PostgreSQL** for data persistence.

## Installation

### Prerequisites
- Java 17 or later
- Maven 3.8+
- Node.js and npm (for frontend React app)
- PostgreSQL Database

### Steps
1. Clone repository
2. Build backend with Maven:
   ```bash
   mvn clean install -DskipTests
3. The database is set up when application is by running the Liquibase migrations. 
   Add any new scripts required to `src/main/resources/changelogs/master-changelog.xml`.
4. Make the following changes to `src/main/resources/application.yml`:
   1. Before first run: 
      - update `spring.jpa.hibernate.ddl-auto` to `create`
      - update `spring.liquibase.enabled` to `false`
   2. After first run:
      - update `spring.jpa.hibernate.ddl-auto` to `validate`
      - remove `spring.liquibase.enabled` (default is `true`)

## Running the Application
1. Start docker
   ```bash
   cd tao
   docker-compose up
2. Start the backend service:
   ```bash
   cd tao
   mvn spring-boot:run
3. Navigate to the frontend directory and start the React app:
   ```bash
   cd tao-interview-ui
   npm install
   npm start
   ```
   The application will be available at http://localhost:3000 for the frontend and http://localhost:8080 for the backend.

## API Endpoints
For more detailed API documentation, refer to the Swagger UI at 
http://localhost:8080/swagger-ui/index.html.

## Configuration
- **Database**: Set up your database connection details in the `application.yml`.
  - Create new `application.yml` for different environments

## Technologies Used
- **Backend**: Spring Boot, Spring Security, Maven, Liquibase
- **Frontend**: React, Axios
- **Database**: PostgresSQL
- **Tools**: MapStruct, Swagger for API documentation