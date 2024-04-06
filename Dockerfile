#
# Build stage
#
FROM gradle:7.6.4-jdk17-alpine AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build --no-daemon

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /tmp
ARG jar_file=/app/build/libs/*jar
COPY --from=build ${jar_file} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
