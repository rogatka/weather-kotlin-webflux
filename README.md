# Weather Service

Simple application that shows current temperature for all cities of country (Russia)
The application first goes to the Mongo DB for cities and then makes a request to the Weather API for each city
There is also an external cache (Redis) for cities and weather responses 

### Technologies:
Kotlin, Maven, Spring Boot 3.3.1, Liquibase, WebFlux, MongoDB, Redis

### Liquibase DB migration

- To run DB migrations, execute the following command from the project directory:
```
mvn liquibase:update
```

### Running app locally

- Use 'local' profile:
```
-Dspring.profiles.active=local
```

### Weather-API restrictions
Only for non-commercial use and less than 10.000 daily API calls