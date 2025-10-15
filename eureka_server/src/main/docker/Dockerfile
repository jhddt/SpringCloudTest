# 阿里云的镜像使用，参考https://cr.console.aliyun.com/cn-hangzhou/instances/artifact
FROM dragonwell-registry.cn-hangzhou.cr.aliyuncs.com/dragonwell/dragonwell:8
VOLUME /tmp
# 下面jar包的名称为springboot项目打包完成的jar包名称

ADD eureka_server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 1234
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]