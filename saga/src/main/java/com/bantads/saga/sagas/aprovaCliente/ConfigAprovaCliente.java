package com.bantads.saga.sagas.aprovaCliente;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class ConfigAprovaCliente {
    @Bean
    public static Queue queueAprovaContaProducer() {
        return new Queue("saga-conta-aprova-init", true);
    }

    @Bean
    public static Queue queueAprovaContaReceiver() {
        return new Queue("saga-conta-aprova-end", true);
    }

    @Bean
    public static Queue queueAprovaContaAuthProducer() {
        return new Queue("saga-auth-aprova-init", true);
    }

    @Bean
    public static Queue queueAprovaContaAuthReceiver() {
        return new Queue("saga-auth-aprova-end", true);
    }
}
