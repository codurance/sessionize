FROM maven:3.8.1-openjdk-11-slim AS prod-build
COPY src  /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=prod-build /home/app/target/*.jar /home/app/app.jar
RUN rm -rf src mvnw* mvnw.cmd pom.xml
RUN ls
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
