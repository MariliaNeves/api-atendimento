package com.atendimento;

import com.atendimento.model.util.RabbitMQConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RabbitMQConfigTest {

    private RabbitMQConfig rabbitMQConfig;

    @Mock
    private ConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rabbitMQConfig = new RabbitMQConfig();
    }

    @Test
    void testServiceRequestQueue() {
        Queue queue = rabbitMQConfig.serviceRequestQueue();
        assertNotNull(queue);
        assertNotNull(queue.getName());
    }

    @Test
    void testAttendantsQueue() {
        Queue queue = rabbitMQConfig.attendantsQueue();
        assertNotNull(queue);
        assertNotNull(queue.getName());
    }

    @Test
    void testRabbitAdmin() {
        RabbitAdmin rabbitAdmin = rabbitMQConfig.rabbitAdmin(connectionFactory);
        assertNotNull(rabbitAdmin);
    }

    @Test
    void testMessageConverter() {
        Jackson2JsonMessageConverter messageConverter = rabbitMQConfig.messageConverter();
        assertNotNull(messageConverter);
    }

    @Test
    void testRabbitTemplate() {
        Jackson2JsonMessageConverter messageConverter = rabbitMQConfig.messageConverter();
        RabbitTemplate rabbitTemplate = rabbitMQConfig.rabbitTemplate(connectionFactory, messageConverter);
        assertNotNull(rabbitTemplate);
        assertNotNull(rabbitTemplate.getMessageConverter());
    }
}
