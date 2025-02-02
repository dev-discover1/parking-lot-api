@echo off

echo Cleaning and Installing the application...
mvn clean install && (
    echo Starting the application...
    mvn spring-boot:run
)

pause
