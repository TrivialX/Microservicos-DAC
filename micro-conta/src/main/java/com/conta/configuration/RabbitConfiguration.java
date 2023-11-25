package com.conta.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue queue1() {
        return new Queue("saga-conta-autocadastro-init", true);
    }

    @Bean
    public Queue queue2() {
        return new Queue("saga-conta-autocadastro-end", true);
    }

    @Bean
    public Queue queue3() {
        return new Queue("atualiza-conta", true);
    }

    @Bean
    public Queue queue4() {
        return new Queue("teste2", true);
    }

    @Bean
    public Queue queue5() {
        return new Queue("teste", true);
    }

    @Bean
    public Queue queue6(){
        return  new Queue("atualiza-movimentacao", true);
    }

    @Bean
    public Queue queue7() {
        return new Queue("atualiza-conta-saga", true);
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
