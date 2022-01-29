FROM adoptopenjdk/openjdk11:alpine-slim
ARG JAR_FILE=./target/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]