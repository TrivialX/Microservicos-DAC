package com.bantads.saga.sagas.autoCadastro;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ConfigAutoCadastro {

    @Bean
    public static Queue queueAutoCadCliente() {
        return new Queue("saga-cliente-autocadastro-init", true);
    }

    @Bean
    public static Queue queueAutoCadClienteReceiver() {
        return new Queue("saga-cliente-autocadastro-end", true);
    }

    @Bean
    public static Queue queueAutoCadGerente() {
        return new Queue("saga-gerente-autocadstro-init", true);
    }

    @Bean
    public static Queue queueAutoCadGerenteReceiver() {
        return new Queue("saga-gerente-autocadastro-end", true);
    }

    @Bean
    public static Queue queueAutoCadConta() {
        return new Queue("saga-conta-autocadastro-init", true);
    }

    @Bean
    public static Queue queueAutoCadContaReceiver() {
        return new Queue("saga-conta-autocadastro-end", true);
    }

    @Bean
    public static Queue queueAutoCadAuth() {
        return new Queue("saga-auth-autocadastro-init", true);
    }

    @Bean
    public static Queue queueAutoCadAuthReceiver() {
        return new Queue("saga-auth-autocadastro-end", true);
    }

}
