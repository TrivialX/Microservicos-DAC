package com.bantads.saga.sagas.alteraPerfil;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigAlteraPerfil {

    @Bean
    public static Queue queueAlteraPerfilCliente() {
        return new Queue("saga-cliente-alteraperfil-init", true);
    }

    @Bean
    public static Queue queueAlteraPerfilClienteReceiver() {
        return new Queue("saga-cliente-alteraperfil-end", true);
    }

    @Bean
    public static Queue queueAlteraPerfilGerente() {
        return new Queue("saga-gerente-alteraperfil-init", true);
    }

    @Bean
    public static Queue queueAlteraPerfilGerenteReceiver() {
        return new Queue("saga-gerente-alteraperfil-end", true);
    }

    @Bean
    public static Queue queueAlteraPerfilConta() {
        return new Queue("saga-conta-alteraperfil-init", true);
    }

    @Bean
    public static Queue queueAlteraPerfilContaReceiver() {
        return new Queue("saga-conta-alteraperfil-end", true);
    }

    @Bean
    public static Queue queueAlteraPerfilAuth() {
        return new Queue("saga-auth-alteraperfil-init", true);
    }

    @Bean
    public static Queue queueAlteraPerfilAuthReceiver() {
        return new Queue("saga-auth-alteraperfil-end", true);
    }
    
}
