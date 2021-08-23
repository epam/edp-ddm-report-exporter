FROM nexus-docker-registry.apps.cicd2.mdtu-ddm.projects.epam.com/openjdk:11.0.7-jre-slim
WORKDIR /app
COPY target/report-exporter-*.jar app.jar
CMD java $JAVA_OPTS -jar app.jar
