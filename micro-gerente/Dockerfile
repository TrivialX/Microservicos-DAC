FROM openjdk:17-jdk-slim

VOLUME /tmp
COPY target/demo-*.jar app.jar

ENTRYPOINT ["java", "-Djava.security.edg=file:dev/./urandom", "-jar", "/app.jar"]