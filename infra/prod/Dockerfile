FROM amazoncorretto:17-alpine-jdk

WORKDIR /app
RUN  rm -rf /app/target/*
COPY ./app /app
RUN  /bin/sh ./mvnw dependency:purge-local-repository clean install
RUN mv /app/target/*.jar /app/app-fastfood-1.jar
CMD ["sh", "-c", " java -jar /app/app-fastfood-1.jar"]

