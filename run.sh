#!/bin/bash

echo "Cleaning and Installing the application..."
mvn clean install && echo "Build successful!" || { echo "Build failed!"; exit 1; }

echo "Starting the application..."
mvn spring-boot:run
