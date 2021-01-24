#
# Build stage
#
FROM maven:3.6.3-openjdk-14-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean verify package

#
# Generate jar
#
FROM openjdk:14
COPY --from=build /home/app/target/*.jar /usr/local/lib/spring.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/spring.jar"]
