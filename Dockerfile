FROM adoptopenjdk:11.0.11_9-jre-hotspot

WORKDIR  user-feature-manager

COPY build/libs/user-feature-manager-0.0.1-SNAPSHOT.jar user-feature-manager.jar

ENTRYPOINT ["java","-jar","user-feature-manager.jar"]

EXPOSE 8080
