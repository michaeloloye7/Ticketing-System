# 🚧 This project is currently in progress  🚧 
# Repository Name: Ticketing-System
# Project Description: building a web-based full-stack IT help desk ticketing system entirely from scratch.
# Built with Java and Spring Boot as part of a CS/IT learning project. 
# Features:
User registration and login - secure account creation with BCrypt password hashing. 

Role Based Access Control - authenticates if the user is a user/technician/admin and gives permissions based on role.

Ticket Creation - authenticated users can create and submit tickets with a title and description. 

Ticket Management - technicians and admins can view and update all tickets by their status. 

# Technologies used: 
Java, Spring Boot, Spring Data JPA, Spring Security (BCrypt), Thymeleaf, and Jakarta Persistence.

# How It Works: 
User Roles:

User - can submit tickets and view/edit their own tickets only

Technician - can view all tickets and update their status

Admin - full access including deleting tickets. 


Ticket Lifecycle: 

OPEN -> IN PROGRESS -> RESOLVED 

# Run the project
  git clone clone https://github.com/moloye7/Ticketing-System.git
  
  cd Ticketing-System
  
  mvn spring-boot:run 
  
  Then visit http://localhost:8080/register in your browser
