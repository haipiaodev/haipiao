FROM openjdk:11

COPY build/libs/user-service.jar /
# no need to copy properties file, since it will be mounted by k8s.
#COPY application.properties /
WORKDIR /
CMD ["java","-jar","user-service.jar"]
