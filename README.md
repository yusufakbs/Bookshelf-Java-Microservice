### Bookshelf Java Microservice

### Overview
The Bookshelf Java Microservice project was developed as a learning exercise to explore and understand various technologies within a microservices architecture. It employs a range of technologies to provide hands-on experience with modern development practices.

### Microservices

1. **Book Service**
   - **API Endpoints:**
     - `GET /v1/book` - Retrieve all books
     - `GET /v1/book/isbn/{isbn}` - Retrieve book by ISBN
     - `GET /v1/book/book/{id}` - Retrieve book by ID

2. **Library Service**
   - **API Endpoints:**
     - `GET /v1/library/{id}` - Retrieve library by ID
     - `POST /v1/library` - Create a new library
     - `PUT /v1/library` - Add a book to a library
     - `GET /v1/library` - Retrieve all libraries
     - `GET /v1/library/count` - Retrieve the number of libraries

3. **Config Server**
   - Manages external configurations for other microservices.

4. **Eureka Server**
   - Service discovery server where microservices register themselves.

5. **Gateway**
   - Routes requests to the appropriate microservices.

### Technologies Used

- **Java**: Main programming language.
- **Kotlin**: Used for models and DTOs to leverage modern language features like data classes and null safety.
- **Spring Boot**: Core framework.
- **Spring Data JPA**: For database interaction.
- **H2 Database**: In-memory database for development and testing.
- **Maven**: Project management and build tool.
- **Spring Cloud Config**: Centralized configuration management.
- **Spring Cloud Netflix Eureka**: Service discovery.
- **Spring Cloud Gateway**: Used as an API Gateway.
- **gRPC**: For high-performance communication between microservices.
- **Zipkin**: Distributed tracing and debugging tool.
- **Spring Boot Actuator**: Monitoring and management tool.

