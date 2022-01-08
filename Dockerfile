FROM java:8
MAINTAINER hobbs 492995407@qq.com
ADD /build/libs/GrandWorldG-0.0.1-SNAPSHOT.jar app.java
ENTRYPOINT ["java","jar","app.java"]
EXPOSE 8080