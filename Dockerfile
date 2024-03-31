FROM amazoncorretto:22-alpine3.17

ARG APP_VERSION
ARG APP_NAME
ENV APP_NAME=${APP_NAME}

  # Set the working directory in the container
WORKDIR /app

  # Copy the JAR file from your target folder to the container
COPY build/libs/${APP_NAME}-${APP_VERSION}.jar /app/${APP_NAME}.jar

  # Make port 8080 available to the world outside this container
EXPOSE 8080

  # Run the JAR file
ENTRYPOINT java -jar /app/${APP_NAME}.jar
