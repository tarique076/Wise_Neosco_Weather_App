# Wise_Neosco_Weather_App

This is a Weather app that can be used to see weather reports of your location. It can also be used to see the weather conditions of other cities. This runs on the local system and requires a local MS SQL database.

## How to Run

1. Clone the repository using GitBash. 

2. Open the application "Weather_app" in any IDE for Java of your choice, eg:- STS, Eclipse, or IntelliJ. This application will be present in the folder in which you cloned the repo.

3. Create an MS SQL Database with the name "weatherAppDB". MSSMS (Microsoft SQL Server Management Studio) can be used to create the database on your local system.

4. Change the database configuration in the application.properties file in the "Weather_app" project. Edit the username and password field according to your database. You might also need to change the port on which your MS SQL server is running. My MS SQL server runs on port 51289, if your server runs on a different port you will have to change the port in the datasource.url accordingly.
```
#db specific properties
spring.datasource.url=jdbc:sqlserver://localhost:51289;encrypt=true;trustServerCertificate=true;databaseName=weatherAppDB
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.datasource.username=sa
spring.datasource.password=qwerty@123
```  

5. The "Weather_app" application will by default run on port 8080. If you want to change the port on which the "Weather_app" runs, you can add this code to the application.properties. Change the port number as per your need.
```
server.port=8080
```

6. Run the application.

7. Open a browser and hit the following endpoint.
```
http://localhost:8080/
```

If you changed the port no. for the application, you can use that port no. in the uri instead of 8080.

8. The application will ask for location permission. Allow it to see the weather at your current location.


## Features:- 
  - Weather report of current location.
  - Search for weather reports of other locations by typing in the city name in the search field and hitting search.
  - Check the hourly forecast by hitting the hourly forecast button. If you haven't searched for another city, it will show the forecast for your current location, else it will show the forecast for the city that you have searched.

### Caching:- 
  - The application first checks the Local DB for the data. If data for the current location is present with time not earlier than 30 minutes, it fetches data from Local DB, otherwise, it uses the Open Weather API.
  - For the hourly forecast, it checks for today's date. If hourly forecast data is present for today's date in the DB it fetches it from the local db else uses the Open Weather API.
  

## Tools and Tech-stack used
1. Java
2. Springboot
3. JPA
4. MS SQL
5. Lombok
6. Thymeleaf
7. Spring Tool Suite (STS)
