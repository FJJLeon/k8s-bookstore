# demo project for CICD-Build image using bookstore[![Build Status](https://www.travis-ci.org/FJJLeon/k8s-bookstore.svg?branch=master)](https://www.travis-ci.org/FJJLeon/k8s-bookstore)

* 一个简单的springboot后端，使用maven构建，tomcat部署
* 使用 Travis.CI 平台作CICD，并自动打包并制作 docker 镜像上传到 docker Hub，详见 .travis.yml 和 Dockerfile
* [前端](https://github.com/FJJLeon/k8s-bookstore-front)是一个 React 项目部署在 nginx 上

### Problems：
* 后端mvn test 时需要有数据库，因而在 travis的配置中需要加入数据库初始化信息
* Dockerfile编写中 CMD和ENTRYPOINT指令理解，注意后者无法被docker run时的指令覆盖，若后者运行一个命令后结束，则整个container都会exit
* 无论访问后端静态页面还是api与本地Ecilpse运行存在差别，需要形如 https://[ip]:[port]/[war包名]/[api]，而本地运行不需要war包名(不包括.war)
* 访问数据库需要修改springboot的配置文件url，注意数据库账户信息一致，k8s数据库可以直接在mysql的shell插入初始化
* 跨域访问需要配置 cors 

### Reference
* [TravisCI: Setup MySQL Tables+Data before running Tests](https://andidittrich.de/2017/06/travisci-setup-mysql-tablesdata-before-running-tests.html)
* [dockerfile 介绍](https://www.cnblogs.com/boshen-hzb/p/6400272.html)
* [SpringBoot配置Cors解决跨域请求问题](https://www.cnblogs.com/yuansc/p/9076604.html)
* [Deploy a Spring Boot WAR into a Tomcat Server](https://www.baeldung.com/spring-boot-war-tomcat-deploy)