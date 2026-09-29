FROM tomcat:11-jdk21

RUN rm -rf /usr/local/tomcat/webapps/*

COPY web/ /usr/local/tomcat/webapps/ROOT/

COPY lib/mysql-connector-j-9.6.0.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/
COPY lib/jbcrypt-0.4.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/

COPY src/ /tmp/src/

RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

RUN javac \
    -cp "/usr/local/tomcat/lib/servlet-api.jar:/usr/local/tomcat/webapps/ROOT/WEB-INF/lib/mysql-connector-j-9.6.0.jar:/usr/local/tomcat/webapps/ROOT/WEB-INF/lib/jbcrypt-0.4.jar" \
    -d /usr/local/tomcat/webapps/ROOT/WEB-INF/classes \
    /tmp/src/com/codecraftershub/servlet/*.java

EXPOSE 8080

CMD ["catalina.sh", "run"]