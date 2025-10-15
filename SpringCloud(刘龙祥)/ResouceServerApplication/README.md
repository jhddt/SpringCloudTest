# OAuth2 Resource Server Application

基于Spring Boot 3.2.0和Spring Security 6.x构建的OAuth2资源服务器，支持JDK17。

## 🚀 功能特性

- **OAuth2资源服务器** - 保护受保护的资源端点
- **JWT Token验证** - 使用JWT访问令牌验证请求
- **Spring Boot 3.x** - 支持最新的Spring Boot版本
- **JDK 17** - 使用最新的Java版本
- **RESTful API** - 提供REST API接口

## 🛠️ 技术栈

- **Java**: JDK 17
- **Spring Boot**: 3.2.0
- **Spring Security**: 6.x
- **Spring OAuth2 Resource Server**: 1.2.0
- **Apache HttpClient**: 5.x
- **Maven**: 3.6+

## 📋 环境要求

- JDK 17+
- Maven 3.6+
- OAuth2认证服务器 (auth_server)

## 🔧 配置说明

### 服务器配置

```yaml
server:
  port: 8080
  address: 0.0.0.0
```

### OAuth2资源服务器配置

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://192.168.159.128:3333
          jwk-set-uri: http://192.168.159.128:3333/oauth2/jwks
```

## 🚀 快速开始

### 1. 启动认证服务器

确保OAuth2认证服务器 (auth_server) 在 `http://192.168.159.128:3333` 运行。

### 2. 启动资源服务器

```bash
cd ResouceServerApplication
mvn clean compile
mvn spring-boot:run
```

资源服务器将在 `http://localhost:8080` 启动。

## 📡 API端点

### 公共端点（无需认证）

- **`GET /test/public`** - 公共测试端点
- **`GET /token/get`** - 获取访问令牌（测试用）

### 受保护端点（需要认证）

- **`GET /test/hello`** - 受保护的资源端点，需要 `SCOPE_all` 权限

## 🔐 使用示例

### 1. 获取访问令牌

```bash
curl http://localhost:8080/token/get
```

### 2. 访问受保护的资源

```bash
# 首先获取token
TOKEN=$(curl -s http://localhost:8080/token/get | grep -o '"access_token":"[^"]*"' | cut -d'"' -f4)

# 使用token访问受保护的资源
curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/test/hello
```

### 3. 直接使用认证服务器获取token

```bash
curl -X POST http://192.168.159.128:3333/oauth2/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -H "Authorization: Basic dGVzdDp0ZXN0" \
  -d "grant_type=client_credentials&scope=all"
```

## 🏗️ 项目结构

```
ResouceServerApplication/
├── src/main/java/com/baorant/resouceserverapplication/
│   ├── ResouceServerApplication.java          # 主启动类
│   ├── adapter/
│   │   └── Oauth2ResourceServerConfiguration.java  # OAuth2资源服务器配置
│   ├── controller/
│   │   ├── TestController.java                # 测试控制器
│   │   └── TokenController.java               # Token获取控制器
│   └── utils/
│       └── HttpClientWithBasicAuth.java       # HTTP客户端工具类
├── src/main/resources/
│   └── application.yml                        # 应用配置
└── pom.xml                                    # Maven配置
```

## 🔄 工作流程

1. **客户端请求** → 向认证服务器获取访问令牌
2. **携带Token** → 在请求头中携带 `Authorization: Bearer <token>`
3. **Token验证** → 资源服务器向认证服务器验证token
4. **返回资源** → 验证通过后返回受保护的资源

## 🔧 部署到虚拟机

### 1. 修改配置

将 `application.yml` 中的认证服务器地址改为虚拟机IP：

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://192.168.159.128:3333
          jwk-set-uri: http://192.168.159.128:3333/oauth2/jwks
```

### 2. 构建JAR包

```bash
mvn clean package -DskipTests
```

### 3. 部署运行

```bash
java -jar target/ResouceServerApplication-0.0.1-SNAPSHOT.jar
```

## 🔍 故障排除

### 常见问题

1. **Token验证失败**
   - 检查认证服务器是否运行
   - 验证JWK Set URI是否正确
   - 确认token是否有效

2. **端口被占用**
   ```bash
   # 查找占用端口的进程
   netstat -ano | findstr :8080
   # 终止进程
   taskkill /PID <PID> /F
   ```

3. **认证服务器连接失败**
   - 检查网络连接
   - 验证认证服务器地址和端口
   - 确认防火墙设置

### 日志查看

应用日志包含详细的调试信息，可以通过以下方式查看：

```bash
# 查看实时日志
tail -f logs/application.log
```

## 📚 相关文档

- [Spring OAuth2 Resource Server官方文档](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)
- [JWT RFC 7519](https://tools.ietf.org/html/rfc7519)
- [OAuth2 RFC 6749](https://tools.ietf.org/html/rfc6749)

## 📄 许可证

本项目采用 MIT 许可证。

## 🤝 贡献

欢迎提交Issue和Pull Request来改进项目。

---

**注意**: 这是一个OAuth2资源服务器，需要与OAuth2认证服务器配合使用。请确保认证服务器正常运行。
