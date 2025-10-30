package com.baorant.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 配置客户端应用启动类
 * 
 * 启动说明：
 * 1. 启动前确保ConfigServerGit服务已运行在192.168.141.128:9006
 * 2. 确保RabbitMQ服务已运行在192.168.141.128:5672
 * 3. 应用启动后会从配置服务器获取配置信息
 * 
 * 常见问题及解决方案：
 * 1. 启动时报错连接不上配置服务器：
 *    - 检查ConfigServerGit是否正常运行
 *    - 检查网络连接是否正常
 *    - 检查bootstrap.yml中的配置服务器地址是否正确
 * 
 * 2. 配置获取为null：
 *    - 检查Git仓库中的配置文件是否存在且格式正确
 *    - 检查ConfigServerGit是否能正确访问Git仓库
 *    - 检查ConfigClient的application name是否与Git配置文件名匹配
 * 
 * 3. 配置刷新不生效：
 *    - 检查RabbitMQ是否正常运行
 *    - 检查management端点是否正确暴露
 *    - 检查@RefreshScope注解是否正确使用
 */
@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

}