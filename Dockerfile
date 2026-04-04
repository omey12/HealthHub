FROM maven:3.9.6-eclipse-temurin-11 AS build

WORKDIR /app

COPY pom.xml .
COPY HealthHub/src/main ./src/main
COPY src/main/webapp/WEB-INF ./src/main/webapp/WEB-INF

RUN mvn clean package -DskipTests

FROM tomcat:9.0-jdk11

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
