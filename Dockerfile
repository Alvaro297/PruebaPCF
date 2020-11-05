FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir upload-dir
ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar"]

