FROM openjdk:11
MAINTAINER hobbs 492995407@qq.com
ADD build/libs/GrandWorldG-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080