FROM arm64v8/openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY ./build/libs/glassofwater-1.0.0-alpha.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]