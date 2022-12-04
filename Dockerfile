FROM openjdk:17.0.2
COPY target/daily-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENV TZ Asia/Shanghai
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=production"]