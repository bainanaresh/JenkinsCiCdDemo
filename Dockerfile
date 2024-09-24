FROM openjdk:17
WORKDIR /appContainer
COPY . target/JenkinsCiCdDemo.jar /appContainer/
EXPOSE 8181
CMD [ "java","-jar","JenkinsCiCdDemo.jar"]