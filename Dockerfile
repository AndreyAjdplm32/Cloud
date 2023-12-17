FROM openjdk:17-oracle
EXPOSE 8081
ADD target/backend-SNAPSHOT.jar diplom.jar
CMD ["java", "-jar", "diplom.jar"]