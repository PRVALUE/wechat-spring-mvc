wechat-spring-mvc
========

## Overview

wechat-spring-mvc is [wechat enterprise api](qydev.weixin.qq.com/wiki/index.php)  project built on top of Spring MVC framework.

## Staging Environment: 

 - address: [wechat-spring-mvc](http://hsbc-staging.prvalue.cn:8080/wechat-spring-mvc/persons)

## Get Start!

### Software Server Requirements

 - Tomcat7 or Glassfish server
 - Java 7 or above
 - MySQL 5.4 or above

### DB Dump

https://github.com/PRVALUE/wechat-spring-mvc/src/main/resources/DB/init.sql

### Configuration
MySQL connection:change the dataSource bean definition in src/main/webapp/WEB-INF/spring/appSerlvet/servlet-context.xml according to your needs.

Name    | Value
--------|------
url     |jdbc:mysql://localhost:3306/TestDB
username|root
password|

### MVC

Component | Path
----------|-----
model | [com/prvalue/wechat/model](https://github.com/PRVALUE/wechat-spring-mvc/src/main/java/com/prvalue/wechat/model/)
view | [webapp/WEB-INF/views](https://github.com/PRVALUE/wechat-spring-mvc/src/main/webapp/WEB-INF/views)
controller | [com/prvalue/wechat/controller](https://github.com/PRVALUE/wechat-spring-mvc/src/main/java/com/prvalue/wechat/controller/)

### Deploy Process

Compile the maven project to produce a wechat-spring-mvc.war, put it under tomcat/webapps/ folder and restart tomcat.
