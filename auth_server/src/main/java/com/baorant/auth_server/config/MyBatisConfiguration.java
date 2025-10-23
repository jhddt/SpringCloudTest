package com.baorant.auth_server.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * MyBatis配置类
 * 用于配置SqlSessionFactoryBean，设置数据源、实体类别名包和Mapper映射文件路径
 */
@Configuration
public class MyBatisConfiguration {

    /**
     * 创建SqlSessionFactoryBean实例并进行相关配置
     * @param dataSource 数据源对象，由Spring自动注入
     * @return 配置完成的SqlSessionFactoryBean实例
     * @throws IOException 当读取Mapper文件时发生IO异常
     */
    @Bean
    @Autowired
    @ConditionalOnMissingBean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置实体类所在的包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.baorant.auth_server.entity");
        // 设置mapper映射文件所在的路径
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = "classpath*:mapper/**.xml";
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver
                .getResources(packageSearchPath));

        return sqlSessionFactoryBean;
    }
}