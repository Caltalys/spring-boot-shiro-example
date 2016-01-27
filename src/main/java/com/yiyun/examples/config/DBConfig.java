package com.yiyun.examples.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig implements EnvironmentAware {
    private RelaxedPropertyResolver dbConfigResolver;
    private Environment env;

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.dbConfigResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean(name="dataSource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dbConfigResolver.getProperty("url"));
        druidDataSource.setUsername(dbConfigResolver.getProperty("username"));
        druidDataSource.setPassword(dbConfigResolver.getProperty("password"));
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(50);
        return druidDataSource;
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
