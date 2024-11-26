FROM openjdk:17-jdk-slim
COPY target/*.jar registrationservice.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","registrationservice.jar"]