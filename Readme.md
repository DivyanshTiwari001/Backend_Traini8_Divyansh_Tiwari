# Traini8

## About
An MVP for a registry for Govt funded Training Centers.


## Intstallation

1. Clone the github repo : 
```sh
git clone "https://github.com/DivyanshTiwari001/Backend_Traini8_Divyansh_Tiwari.git"

```
2. Start the docker container for Postgres DB (testDB)
```sh
docker-compose up -d
```
*Note - docker is required for this else move to /src/resources and make following updates in application.properties
```sh
cd /src/resources
```
open application.properties
```sh
spring.datasource.url=link/to/your/db
spring.spring.datasource.username=username
spring.datasource.password=password
```
3. Run Application
```sh
./mvnw clean spring-boot:run
```

Note - There is a LoadDatabase.java configuration file which adds two entries in database. To avoid this please comment the file.

4. Visit [Swagger-UI](http://localhost:8000/openapi/swagger-ui/index.html) to see API Documentation

Note - Make sure port 8000,8080 and 5432 are free.







