FROM openjdk:8-jdk-alpine
WORKDIR /e-commerce-project
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#RUN mkdir /myvol
#VOLUME /e-commerce-project/myvol
#RUN ["mv", "./my-app/e-commerce-java-project-1.0.0.jar", "app.jar"]
#ENTRYPOINT ["java","-jar","app.jar"]
ENTRYPOINT ["java","-jar","app.jar"]