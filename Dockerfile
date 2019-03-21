FROM openjdk:11-slim
WORKDIR /var/beacon
COPY ./build/libs/beacon-0.0.1-SNAPSHOT.jar /var/beacon/beacon.jar
CMD java -jar beacon.jar