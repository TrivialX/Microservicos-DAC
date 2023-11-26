package com.example.demo.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

    @Bean  Queue queue5() {
        return new Queue("saga-gerente", true);
    }

    @Bean Queue teste() {
        return new Queue("teste", true);
    }

    @Bean Queue queue6() {
        return new Queue("saga-auth-aprova-init", true);
    }

    @Bean Queue queue7() {
        return new Queue("saga-auth-aprova-end", true);
    }

    @Bean
    Queue queue8() {
        return new Queue("saga-auth-autocadastro-init", true);
    }

    @Bean
    Queue queue9() {
        return new Queue("saga-auth-autocadastro-end", true);
    }

    
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
