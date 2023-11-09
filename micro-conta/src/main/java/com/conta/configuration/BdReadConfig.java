//package com.conta.configuration;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = "com.conta.repository.R",
//        entityManagerFactoryRef = "bdReadManager"
//)
//public class BdReadConfig {
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource.read")
//    public DataSource bdReadDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean bdReadManager(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("bdReadDataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.conta.models.R")
//                .build();
//    }
//
//}
