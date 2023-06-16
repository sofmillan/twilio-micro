FROM openjdk:11
EXPOSE 8084
COPY /build/libs/twilio-powerup-0.0.1-SNAPSHOT.jar twilio-powerup.jar
ENTRYPOINT ["java", "-jar","/twilio-powerup.jar"]
