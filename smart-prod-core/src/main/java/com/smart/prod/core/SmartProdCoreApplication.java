package com.smart.prod.core;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.dromara.easyes.starter.config.EasyEsConfigProperties;
import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@ConfigurationPropertiesScan(value = {"com.smart.prod.*.config"})
@EsMapperScan("com.smart.prod.es.mapper") // 开启easy-es扫描
@ForestScan(basePackages = "com.smart.prod.forest")
@ComponentScan(basePackages = {"org.dromara.easyes.starter", "com.smart.prod", "com.smart.prod.core"},
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EasyEsConfigProperties.class))  // springboot3需要添加此注解过滤easy-es依赖
public class SmartProdCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartProdCoreApplication.class, args);
    }

}
