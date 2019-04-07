FROM openjdk:8-slim
MAINTAINER Lagranmoon "lagranmoon@outlook.com"
WORKDIR /var/lib/beacon
COPY beacon-*.jar beacon.jar
COPY application-prod.yaml config/application-prod.yaml
EXPOSE 8090
ENTRYPOINT ["java","-server","-Dspring.profiles.active=prod","-jar","beacon.jar"]
