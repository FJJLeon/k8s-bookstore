FROM tomcat:9-jre8

RUN mvn install -DskipTests=true -Dmaven.javadoc.skip=true

RUN apt-get update \
    && apt-get install -y --no-install-recommends cowsay \
    && rm -rf /var/lib/apt/lists/*

ADD ./target/Mybk-iteration3-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

RUN /bin/bash -c '\necho install ok\n'

ENV PATH "$PATH:/usr/games"

ENTRYPOINT ["cowsay"]
CMD ["Hello, World!"]