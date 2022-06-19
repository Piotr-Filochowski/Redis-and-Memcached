FROM openjdk:17.0.2
COPY target/praca-magisterska.jar praca-magisterska.jar
ENTRYPOINT ["java","-jar","/praca-magisterska.jar"]