# build environment
FROM maven as build
RUN mkdir /src
WORKDIR /src
ADD . .
RUN mvn package -Dmaven.test.skip

# production environment
FROM openjdk:20-slim
RUN mkdir /app
WORKDIR /app
COPY --from=build /src/target/Booked-API-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "Booked-API-0.0.1-SNAPSHOT.jar"]
