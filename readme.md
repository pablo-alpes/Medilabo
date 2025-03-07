# Medilabo Microservices Application

Juan Pablo Miranda, 7th march 2025.

## Description
A microservices-based Spring Boot application for managing medical records, patient information, and risk profile of diabetes on a simple rule engine provided by the client based on word matching.

## Architecture & Components
The solution deploys a microservice architecture to ensure proper scalability of the solution and ensure independency of the services. The services are as follows:
* API Gateway
* Eureka Server
* Frontend service
* Patient service
* Medical record service
* Risk service
* Shared libraries: DTO and Feign clients (shared interface)

### Microservices Architecture
1. **API Gateway Service** (Port: 5002)
   - Entry point for all client requests
   - Handles request routing and load balancing
   - Implements authentication and authorization
   - It has access to all services and act as a single entry point.

2. **Eureka Server** (Port: 8761)
   - Service discovery for microservices

3. **Patient Service** 
   - Manages patient information
   - Connects to MySQL database

4. **Medical Record Service**  
   - Manages medical records
   - Connects to MongoDB

5. **Risk Service** 
   - Provides risk profile of diabetes based on rule engine
   - Connects to other services via Feign clients

6. **Frontend Service**
   - Provides the user interface for the application

On top the data architecture behind comprises a MySQL database that is 3NF normalized and a mongoDB.
Plus simple rule engine that is based on word matching and security layer handle login and password in memory.

### Service Communication
- Asynchronous communication via REST APIs.
- Service discovery using Eureka Server.
- Feign clients to ensure easy communication between services.

## Technologies
- Backend:
  - Java 22
  - Spring Boot 3.4.1 
  - Spring Cloud
  - Spring Security 
  - Spring  Gateway 
  - Feign 
  - Spring Data JPA
  - MySQL
  - MongoDB
- Frontend:
  - Thymeleaf

## Security
* The solution has an in-memory authentication system for admin only and it is prompted once it launches the service.
* A network segregation on 2 networks using Docker (frontend and backend) is implemented to ensure that the services are not accessible from the outside world. Only the API is exposed.

## API Endpoints
This webapp is all handled by the frontend service and the API Gateway and they just for information.

### Patient Service
- GET `/backend/patients` - List all patients
- POST `/backend/patients/update/{id}` - Update patient
- GET `/backend/patients/delete/{id}` - Delete patient

### Medical Record Service
- GET `/patients/record/get/{id}` - Get patient record
- PUT `/patients/record/update/{id}` - Update patient record

### Risk Service
- GET `/patients/risk/{id}` - Get risk profile

## Running the Application
Designed to run docker desktop on your machine. It can also be used as an image and posted to a cloud provider.
### Prerequisites
- Docker
- Docker Compose

### Steps
1. Start docker desktop on your machine.

2. Clone the repository in your computer:
```bash
git clone https://github.com/pablo-alpes/Medilabo.git
```
3. In your command line, go to the root folder of the project, and launch the Docker compose:
```bash
docker-compose -f docker-compose.yml up --build
```
NB: -f option in the docker-compose command specifies an alternate compose file otherwise used the default setting.

4. The services will be accessible at the following URLs:
- API Gateway and single access point: `http://localhost:5002`
- Eureka Dashboard: `http://localhost:8761`

5. To stop the services:
```bash
docker-compose down
```