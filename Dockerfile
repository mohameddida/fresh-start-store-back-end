FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /

# Copy the JAR file into the container
COPY target/freshstartapi.jar ./

# Expose the port your app runs on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "freshstartapi.jar"]