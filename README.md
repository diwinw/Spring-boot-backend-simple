# Spring-boot-backend-simple

## Getting Started
### Prerequisites
- Java 8 or higher
- Postgres database

### Running docker file
Go to resource path and run with the command
```
docker-compose up -d
```


### Running the Application

1. Clone the repository.
2. Update the database configuration in src/main/resources/application.properties with your Postgres credentials.
```
spring.datasource.url=jdbc:postgresql://{yourHostUrl}:{}yourPort}/{dbName}
spring.datasource.username={userName}
spring.datasource.password={password}

spring.flyway.url=jdbc:postgresql://{yourHostUrl}:{}yourPort}/{dbName}
spring.flyway.user={userName}
spring.flyway.password={password}
```
3. Build the application with the command .
```
./mvnw clean package
```
4. Run the application with the command.
```
java -jar target/stock-0.0.1-SNAPSHOT.jar
```

The application will start on http://localhost:8081.


### Running the Application

Swagger documentation is available at http://localhost:8081/swagger-ui/index.html#/.

### Running Unit Tests
Unit tests can be run with the command
```
./mvnw clean test
```

### Generating Test Coverage Reports
Test coverage reports can be generated with the command
```
./mvnw clean test jacoco:report
```
The coverage report will be available at
```
target/site/jacoco/index.html
```



### Built With
- Spring Boot
- Postgres
- Swagger
- Junit
- JWT
