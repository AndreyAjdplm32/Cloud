FROM openjdk:17-oracle
EXPOSE 8085
ADD /target/backend-0.0.1-SNAPSHOT.jar diplom.jar
CMD ["java", "-jar", "diplom.jar"]