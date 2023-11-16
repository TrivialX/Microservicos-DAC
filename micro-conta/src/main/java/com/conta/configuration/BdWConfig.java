package com.conta.configuration;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = "com.conta.repository.CUD",
        entityManagerFactoryRef = "cudEntityManager",
        transactionManagerRef = "cudTransactionManager")
public class BdWConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean cudEntityManager() {
        final LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(cudDataSource());
        em.setPackagesToScan("com.conta.models.CUD");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();

        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("spring.jpa.show-sql", "true");

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public DataSource cudDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://postgres-conta-cud:5432/postgres");
        dataSource.setSchema("public");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgress");

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager cudTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(cudEntityManager().getObject());
        return transactionManager;
    }


}
