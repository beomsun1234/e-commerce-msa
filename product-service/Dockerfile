FROM adoptopenjdk/openjdk11:alpine-slim
ARG JAR_FILE=/var/lib/jenkins/workspace/msa/order-service/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]