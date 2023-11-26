package com.bantads.saga.sagas.deleteGerente;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class ConfigDeleteGerente {
    
    @Bean
    public static Queue queueDeleteGerente() {
        return new Queue("saga-gerente-deletegerente-init", true);
    }

    @Bean
    public static Queue queueDeleteGerenteReceiver() {
        return new Queue("saga-gerente-deletegerente-end", true);
    }

    @Bean
    public static Queue queueContaDeleteGerente() {
        return new Queue("saga-conta-deletegerente-init", true);
    }

    @Bean
    public static Queue queueContaDeleteGerenteReceiver() {
        return new Queue("saga-conta-deletegerente-end", true);
    }

    @Bean
    public static Queue queueAuthDeleteGerente() {
        return new Queue("saga-auth-deletegerente-init", true);
    }

    @Bean
    public static Queue queueAuthDeleteGerenteReceiver() {
        return new Queue("saga-auth-deletegerente-end", true);
    }

  

    
}

