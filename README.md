# user-feature-manager
The backend spring-boot micro-service for user and feature management

### API documentation

The user-feature-manager exposes the following APIs:

![](images/api-documentation.PNG?raw=true "Title")

Appropriate status and response resources are returned with suitable HTTP status codes in case of problems 

For more details, visit - http://localhost:8080/user-feature-manager/swagger-ui/index.html, once the service is running in Debug mode

### Use cases Implemented
- User can register
- User can log in
- Admin User can create a feature
- Admin User can create a role
- Admin User can retrieve all the features
- Admin User can retrieve all the roles
- Admin User can enable a feature to a User
- Admin User can assign a role to a User
- Any User can see the features enabled for them and all the globally enabled features

### Running the service
The service starts up in with DEBUG flag enabled - thus the API documentation and the H2 Database console endpoints are whitelisted from authentication.
This can be disabled in the application.properties. Debug logs are disabled by default, and they can also be enabled in the application.properties.

- The easiest way to get the service up is by running the docker - ```docker run sameeracodes/user-feature-manager```
- Alternatively, the jar can be built after downloading the source using - ```gradlew build```, and then executed
- Alternatively, ```gradlew bootrun``` can also be executed
- API Documentation is available at http://localhost:8080/user-feature-manager/swagger-ui/index.html
- H2 database console is available at http://localhost:8080/user-feature-manager/h2-console
- For consuming the API endpoints as an admin user, the following jwt token can be used -
  - eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOlt7ImF1dGhvcml0eSI6IkFETUlOIn1dLCJleHAiOjE2NDUzMTU0MjEsImlhdCI6MTY0Mzg0NDI1MX0.QwaSHFVLeg16jg7FEOky09izWoMkInWmRn73Zs45G_o
- For consuming the API endpoints as a regular user, the following jwt token can be used -
  - eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW0iLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJSRUdVTEFSIn1dLCJleHAiOjE2NDUzMTU0MjEsImlhdCI6MTY0Mzg0NDI2NH0.W3JhUWnoCEe5B_EeJHsYuWwp_YPgjk-ihuDUxKPL2GI

### Developer Notes
- The CI with **Github Actions** - go to the actions tab above
    - Builds on every commit to the master and on every pull request to the master
    - Executes gradle build, i.e., compilation, unit tests and integration tests
    - Builds a docker image and publishes it to a repository in docker hub; this image can be used to deploy the service
- Aspect **(Spring AOP)** is used for logging API calls made to the application and debug logs in all the methods
- Different model is used for storage to the database and for API communication
- For mapping and converting objects to different models, **MapStruct** library is used, to avoid writing mapping code
- **Lombok** is used to avoid boilerplate code
- **Spring Security** and JWT is used for authentication
- **H2** in-memory database is used for data storage
- **Spring Data JPA** is used for persistence abstraction
- **Openapi Swagger** is used for API documentation
- The JWT token expiry date is intentionally set for a long time, to enable ease of manual testing, although typically this is same as the session timeout
- ##### TO DO:
  - validation of the incoming REST resources
  - complete suite of unit and integration tests
  - java-docs for public methods
  - use a parameter store instead of application.properties
  - paging