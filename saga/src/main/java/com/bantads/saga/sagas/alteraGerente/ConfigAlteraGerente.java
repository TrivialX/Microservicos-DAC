package com.bantads.saga.sagas.alteraGerente;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class ConfigAlteraGerente {

    @Bean
    public static Queue queueAlteraGerenteReceiver() {
        return new Queue("saga-gerente-alteragerente-end", true);
    }

    @Bean
    public static Queue queueAlteraGerente() {
        return new Queue("saga-gerente-deletegerente-init", true);
    }

    @Bean
    public static Queue queueAuthAlteraGerente() {
        return new Queue("saga-auth-alteragerente-init", true);
    }

    @Bean
    public static Queue queueAuthAlteraGerenteReceiver() {
        return new Queue("saga-auth-alteragerente-end", true);
    }

}
