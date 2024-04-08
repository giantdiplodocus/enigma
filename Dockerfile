FROM openjdk:21-jdk
LABEL authors="jaboobacker"

COPY target/Enigma-1.0.0.jar Enigma-1.0.0.jar
ENTRYPOINT ["java", "-jar","Enigma-1.0.0.jar"]