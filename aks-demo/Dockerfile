FROM openjdk:8-jdk-alpine

# Create a group and user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
WORKDIR /app
COPY ./target/aks-demo-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]