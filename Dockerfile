#
# Build stage
#
FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM eclipse-temurin:8u362-b09-jre
COPY --from=build /target/les-recettes-de-mamie-and-co-1.0.jar demo.jar
# ENV PORT=5000
EXPOSE 5000
ENTRYPOINT ["java","-jar","demo.jar"]