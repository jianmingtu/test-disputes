#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM maven:3.8.5-jdk-8-slim as build

ARG APP_NAME=justin-disputes-api
ARG MVN_PROFILES=splunk
ARG SKIP_TESTS=true

WORKDIR /${APP_NAME}

COPY . .

RUN mvn -B clean package \
		-DskipTests=${SKIP_TESTS} \
	    -P ${MVN_PROFILES} 

#############################################################################################

#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM eclipse-temurin:8-jre-jammy

ARG APP_NAME=justin-disputes-api
EXPOSE 8080
WORKDIR /

COPY --chown=1001:1001 --from=build ./${APP_NAME}/target/${APP_NAME}-*.jar /app/service.jar

USER 1001:1001

CMD ["java", "-jar", "/app/service.jar"]
############################################################################################
