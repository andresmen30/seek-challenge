FROM amazoncorretto:17.0.13
EXPOSE 8080
ADD target/seek-challenge-0.0.1-SNAPSHOT.jar seek-challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java" , "-jar" , "seek-challenge-0.0.1-SNAPSHOT.jar" ]