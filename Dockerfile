#使用Amazon Corretto的JDK是Java 17，alpine是輕量的Linux發行版
FROM amazoncorretto:17-alpine

WORKDIR /home

#複製jar到容器中
COPY ./bmp.jar /home/bmp.jar

#設定外部port
EXPOSE 8080

#啟動專案
ENTRYPOINT ["java", "-jar", "/home/bmp.jar"]