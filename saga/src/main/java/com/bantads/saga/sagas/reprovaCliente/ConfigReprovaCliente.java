package com.bantads.saga.sagas.reprovaCliente;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class ConfigReprovaCliente {

    @Bean
    public static Queue queueReprovaContaProducer() {
        return new Queue("saga-conta-reprova-init", true);
    }

    @Bean
    public static Queue queueReprovaContaReceiver() {
        return new Queue("saga-conta-reprova-end", true);
    }

    @Bean
    public static Queue queueReprovaContaAuthProducer() {
        return new Queue("saga-auth-reprova-init", true);
    }

    @Bean
    public static Queue queueReprovaContaAuthReceiver() {
        return new Queue("saga-auth-reprova-end", true);
    }
}
