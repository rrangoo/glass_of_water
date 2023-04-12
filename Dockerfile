FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY ./build/libs/glassofwater-1.0.0-alpha.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
