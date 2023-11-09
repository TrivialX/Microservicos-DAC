//package com.conta.configuration;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = "com.conta.repository.CUD",
//        entityManagerFactoryRef = "bdWriteManager"
//)
//public class BdWriteConfig {
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource.write")
//    public DataSource bdWriteDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean bdWriteManager(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("bdWriteDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.conta.models.CUD")
//                .build();
//    }
//
//}
