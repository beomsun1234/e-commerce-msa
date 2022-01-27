FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} test.jar
ENTRYPOINT ["java", "-jar", "test.jar"]