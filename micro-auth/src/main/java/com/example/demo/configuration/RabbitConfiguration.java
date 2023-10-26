package com.example.demo.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue queue1() {
        return new Queue("aprova-cliente", true);
    }

    @Bean
    public Queue queue2() {
        return new Queue("reprova-cliente", true);
    }

    @Bean
    public Queue queue3() {
        return new Queue("saga-autocadastro", true);
    }

    @Bean
    public Queue queue4() {
        return new Queue("saga-altera-user", true);
    }

    @Bean Queue queue5() {
        return new Queue("saga-gerente", true);
    }

    @Bean Queue teste() {
        return new Queue("teste", true);
    }
}
