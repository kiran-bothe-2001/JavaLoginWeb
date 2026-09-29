FROM tomcat:11-jdk21

RUN rm -rf /usr/local/tomcat/webapps/*

COPY web/ /usr/local/tomcat/webapps/JavaLoginWeb/
COPY lib/*.jar /usr/local/tomcat/lib/

RUN mkdir -p /tmp/classes
COPY src/ /tmp/src/

RUN javac -cp "/usr/local/tomcat/lib/servlet-api.jar:/usr/local/tomcat/lib/*.jar" -d /usr/local/tomcat/webapps/JavaLoginWeb/WEB-INF/classes /tmp/src/com/codecraftershub/servlet/*.java

EXPOSE 8080

CMD ["catalina.sh","run"]
