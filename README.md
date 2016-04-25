# json-great-circle
spring boot application, exercise in parsing json data then filtering based on longitude and latitude
needs java 1.8 to run, 
mvn 3.3.9, built using spring 1.3.3

1) run as jar:
mvn clean compile assembly:single
java -jar target/nearby-0.0.1-SNAPSHOT.jar #with exercise parameters hardcoded
or explicitly set as parameters:
java -jar target/nearby-0.0.1-SNAPSHOT.jar --nearby.radius_km=100 --nearby.base.coords=-6.2592576,53.3381985 --nearby.people.url=https://gist.github.com/brianw/19896c50afa89ad4dec3/raw/6c11047887a03483c50017c1d451667fd62a53ca/gistfile1.txt


2) run it via spring:boot
mvn spring-boot:run

