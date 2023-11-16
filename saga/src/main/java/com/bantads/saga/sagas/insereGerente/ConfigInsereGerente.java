package com.bantads.saga.sagas.insereGerente;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigInsereGerente {

    @Bean
    public static Queue queueInsereGerente() {
        return new Queue("saga-gerente-inseregerente-init", true);
    }

    @Bean
    public static Queue queueInsereGerenteReceiver() {
        return new Queue("saga-gerente-inseregerente-end", true);
    }

    @Bean
    public static Queue queueContaInsereGerente() {
        return new Queue("saga-conta-inseregerente-init", true);
    }

    @Bean
    public static Queue queueContaInsereGerenteReceiver() {
        return new Queue("saga-conta-inseregerente-end", true);
    }

     @Bean
    public static Queue queueAuthInsereGerente() {
        return new Queue("saga-auth-inseregerente-init", true);
    }

    @Bean
    public static Queue queueAuthInsereGerenteReceiver() {
        return new Queue("saga-auth-inseregerente-end", true);
    }


}
