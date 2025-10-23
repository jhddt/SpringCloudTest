package com.baorant.apigateway.filter;

import com.baorant.apigateway.Utis.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志过滤器，用于记录API网关的请求信息
 * 实现了GlobalFilter接口，对所有请求生效
 * 实现了Ordered接口，可以设置过滤器执行顺序
 */
@Component
public class LogFilter implements GlobalFilter, Ordered {

    //记录日志
    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    /**
     * 过滤器核心方法，记录请求的相关信息
     * 
     * @param exchange ServerWebExchange对象，包含HTTP请求和响应信息
     * @param chain GatewayFilterChain对象，表示过滤器链
     * @return Mono<Void> 异步处理结果
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String ipaddr = IpUtil.getIpAddr(request);  // 请求的IP地址
        String method = request.getMethod().name();  //HTTP方法
        String interfaceMethod = request.getPath().value();// 请求路径

        //响应的消息体
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String nowString = df.format(new Date());

        String msg = nowString + ": IP地址:" + ipaddr + ",请求方法:" + method + ", 请求路由:" + interfaceMethod;
        logger.info(msg);

        System.out.println("MyLogFilter");
        return chain.filter(exchange);
    }

    /**
     * 获取过滤器执行顺序
     * 
     * @return int 返回0，表示最高优先级
     */
    @Override
    public int getOrder() {
        return 0;
    }
}