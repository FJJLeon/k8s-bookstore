FROM tomcat:9-jre8

WORKDIR /usr/local

RUN apt-get update \
    && apt-get install -y --no-install-recommends cowsay \
    && apt-get install -y --no-install-recommends vim \
    && rm -rf /var/lib/apt/lists/*
ENV PATH "$PATH:/usr/games"

ADD ./target/*.war /usr/local/tomcat/webapps/

ENV TZ=Asia/Shanghai
EXPOSE 8080
EXPOSE 3306
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone