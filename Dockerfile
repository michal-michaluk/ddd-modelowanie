FROM openjdk:17-jdk
VOLUME /tmp
COPY ./build/docker/*.jar ./build/docker/image/app.jar
ENTRYPOINT ["java","--enable-preview","-jar","./build/docker/image/app.jar"]
EXPOSE 8080
