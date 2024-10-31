##Bank Account Web Application

A simple bank account web application built with Spring Boot, Spring Security, and SQLite. The application allows users to register, log in, and access secure account-related features. This project serves as a foundational template for implementing authentication and authorization with Spring Security and a database.
Features

    User Registration: Allows new users to create an account.
    User Login: Authenticates users against credentials stored in an SQLite database.
    Secure Access: Restricts access to sensitive account information to authenticated users only.
    Database Storage: Stores user information in an SQLite database, supporting persistent user data.

Technologies

    Spring Boot: Framework for creating Java-based applications.
    Spring Security: Authentication and authorization management.
    SQLite: Lightweight, file-based SQL database for persistent storage.
    Thymeleaf: Template engine for rendering HTML views.

Getting Started
Prerequisites

    Java 17 or higher (or another version compatible with your setup)
    Maven for dependency management and project setup
    SQLite (driver included in the dependencies)

Installation

    Clone the Repository

    bash

git clone https://github.com/your-username/bank-account-web-app.git
cd bank-account-web-app

Configure the Database

Ensure the following configurations are set in src/main/resources/application.properties:

properties

spring.datasource.url=jdbc:sqlite:bank.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.initialization-mode=always

Install Dependencies

Run the following command to download all required dependencies:

bash

mvn clean install

Run the Application

bash

    mvn spring-boot:run

    The application should be available at http://localhost:8080.

Project Structure

    src/main/java/com/example/bank: Contains the main application logic.
        model: Contains entity classes for database tables.
        repository: Manages data access and CRUD operations.
        service: Handles user details and authentication logic.
        controller: Manages web requests and routing.
        WebSecurityConfig: Configures Spring Security for authentication.
    src/main/resources/templates: Contains the HTML files for login, registration, and other web pages.
        login.html: Login page.
        register.html: Registration page.

How to Use
Register a New User

    Navigate to http://localhost:8080/register.
    Fill out the registration form with a username and password.
    Submit the form to create a new user account.

Log in

    Go to http://localhost:8080/login.
    Enter your username and password.
    If authentication is successful, you will be redirected to a secure area (e.g., account dashboard).

Code Overview

    User Entity (User): Defines the structure of the users table in SQLite, containing username, password, and role.
    User Repository (UserRepository): Provides CRUD operations and user retrieval by username.
    CustomUserDetailsService: Implements UserDetailsService to load user information for Spring Security.
    WebSecurityConfig: Configures HTTP security, login, and logout, using the custom UserDetailsService.

Development Notes

    Password Encryption: Passwords are encrypted with BCrypt before storage to enhance security.
    Spring Security: Configured to permit access to specific pages like /register and /login while securing others.
    Database Initialization: SQLite database is initialized automatically by Spring Boot.

Future Enhancements

    Add a user profile or dashboard page to view account details.
    Implement features like balance checks, transaction history, and account management.
    Add more robust error handling for login and registration forms.
    Migrate to a production-ready database like PostgreSQL or MySQL if needed.
