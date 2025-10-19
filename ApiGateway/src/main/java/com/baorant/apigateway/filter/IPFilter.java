package com.baorant.apigateway.filter;

import com.baorant.apigateway.Utis.IpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * IP白名单过滤器
 * 用于限制只能特定IP地址访问网关
 */
@Component
public class IPFilter implements GlobalFilter, Ordered {

    /**
     * IP白名单列表，从配置文件中读取
     * 默认值为127.0.0.1
     */
    @Value("${filter.ip.whitelist:127.0.0.1}")
    private String strIPWhitelist;

    /**
     * 白名单功能是否启用的配置，从配置文件中读取
     * 默认值为true（启用）
     */
    @Value("${filter.ip.whitelistenabled:true}")
    private String whitelistEnabled;

    /**
     * 过滤器核心方法，检查请求IP是否在白名单中
     * 
     * @param exchange ServerWebExchange对象，包含HTTP请求和响应信息
     * @param chain GatewayFilterChain对象，表示过滤器链
     * @return Mono<Void> 异步处理结果
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 检查白名单功能是否启用
        if (!"true".equalsIgnoreCase(whitelistEnabled)) {
            return chain.filter(exchange);
        }

        ServerHttpRequest request = exchange.getRequest();
        String ipAddr = IpUtil.getIpAddr(request);

        List<String> whitelist = Arrays.asList(strIPWhitelist.split(","));
        // 检查IP是否在白名单中
        if (!whitelist.contains(ipAddr)) {
            System.out.println("IP地址 " + ipAddr + " 不在白名单中，拒绝访问");
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

            String responseBody = "{\"errorcode\":\"00001\", \"errmsg\": \"IpAddr is forbidden![" + ipAddr + "]\"}";
            DataBuffer buffer = response.bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        }

        return chain.filter(exchange);
    }

    /**
     * 获取过滤器执行顺序
     * 
     * @return int 返回0，表示最高优先级
     */
    @Override
    public int getOrder() {
        return 0; // 优先级最高
    }
}