FROM tomcat:9-jre8

ADD ./target/Mybk-iteration3-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

RUN /bin/bash -c '\necho install ok\n'