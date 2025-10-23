package com.baorant.resouceserverapplication.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * OAuth2资源服务器配置类
 * 配置资源服务器的安全策略，包括JWT解码器和权限转换器
 */
@Configuration
@EnableWebSecurity
public class Oauth2ResourceServerConfiguration {

    /**
     * 配置安全过滤器链
     * 定义URL访问权限和OAuth2资源服务器的JWT配置
     * @param http HttpSecurity对象，用于配置安全策略
     * @return SecurityFilterChain 安全过滤器链
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/test/public", "/token/**").permitAll()
                .requestMatchers("/test/hello").hasAuthority("SCOPE_all")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .decoder(jwtDecoder())
                    .jwtAuthenticationConverter(jwtAuthenticationConverter())
                )
            );
        
        return http.build();
    }

    /**
     * 创建JWT解码器
     * 通过JWK Set URI验证JWT令牌
     * @return JwtDecoder JWT解码器实例
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        // 从认证服务器获取JWK Set来验证JWT
        return NimbusJwtDecoder.withJwkSetUri("http://192.168.141.128:3333/oauth2/jwks").build();
    }

    /**
     * 创建JWT认证转换器
     * 配置权限前缀和权限声明名称
     * @return JwtAuthenticationConverter JWT认证转换器实例
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("SCOPE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}