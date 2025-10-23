package com.example.feigncloudclientapplication.component;

import com.example.feigncloudclientapplication.service.UserClient;
import org.springframework.stereotype.Component;

/**
 * Feign客户端的降级处理类，当远程调用失败时提供备用方案
 */
@Component
public class FeignClientFallback implements UserClient {
    /**
     * 当远程UserService调用失败时的降级处理方法
     *
     * @param sleep_seconds 睡眠秒数参数（此参数在降级处理中未使用）
     * @return 降级处理返回的默认字符串
     */
    @Override
    public String hello(int sleep_seconds) {
        return "Hi!容错机制已经启动";
    }
}