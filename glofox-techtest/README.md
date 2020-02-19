# GloFox Tech Test

This is my class/bookings api for glofox.
It's a maven project using Java8, Spring Boot and an in-memory h2 database.
My IDE of choice was IntelliJ CE.

### Design:
    - Implentation has been split across separate controllers and services to allow an easier refactor should they be split into separate applications later.
    - I've used the words GymClass to denote classes because of the java keyword clash
    - RestAssured has been used to test the endpoints and integration tests.

## How to run
mvn clean install to run all the tests.
If you import the project into intellij you can run the GlofoxApplication.java file to start the application.

I've included a postman collection with some demo api calls with good data in there.
If you call the /classes one first to create the class followed by the /booking one to reduce the capacity of the chosen class (indicating you're attending).

The application will throw a BookingServiceException if there is no class available.

## Database
Once the application has started you can view the database at http://localhost:8080/h2

username=sa
password=''

as found in glofox/src/main/resources/application.properties