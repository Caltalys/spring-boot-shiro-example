package com.yiyun.examples.config;

import com.yiyun.examples.common.filter.CacheFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@Configuration
public class CommonConfig {
    @Bean
    public FilterRegistrationBean disableCacheFiler() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CacheFilter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }

}
