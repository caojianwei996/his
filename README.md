# 项目总概述

本项目为医院的信息化门诊系统。能满足医院从病人挂号到诊断到缴费的一整套流程的业务需要，为门诊医生和门诊相关的医院工作人员提供便利。

# 项目技术栈

基于Spring Cloud Alibaba开发的一套微服务系统

## 完成了后端的微服务网关功能

利用 Spring Cloud Gateway 作为微服务网关，实现了服务转发、日志记录和访问鉴权等功能。

## 完成了后端的单点登录功能

利用 Spring Authorization Server 实现了单点登录功能，同时继承了 GitHub 和 Gitee 的第三方账号关联登录。

## 完成了后端的微服务治理功能

利用 Nacos 作为注册发现配置中心，利用 Seata 解决了分布式事务问题，利用 Sentinel 实现了异常熔断和服务降级。

## 完成了后端的远程调用功能

利用 Open Feign 实现了同步远程调用，利用 HTTP Interface 实现了异步远程调用，利用 Spring Cloud LoadBalancer 实现了负载均衡。

# 开发与运行环境

OpenJDK 21.0.3  
Nacos 2.4.2.1  
Seata 2.1.0  
MySQL 8.0.39  
PostgresSQL 16.4  
Redis 6.0.16  
MongoDB 8.0.0  
Elasticsearch 8.15  
RabbitMQ 3.9.27

## 开发环境

Windows 11 x64  
Maven 3.9.8

## 运行环境

Ubuntu 24.04 LTS  
