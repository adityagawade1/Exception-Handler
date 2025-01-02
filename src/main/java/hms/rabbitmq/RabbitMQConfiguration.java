package hms.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Autowired
    private ConfigurationPropertiesReader propertiesReader;

    @Bean
    Queue createQueue(){
        return new Queue(propertiesReader.getQueue(),true, false,false);
    }

    @Bean
    DirectExchange createExchange(){
        return new DirectExchange(propertiesReader.getExchange(),true,false);
    }

    @Bean
    Binding createBinding(){
        return BindingBuilder.bind(createQueue()).to(createExchange()).with(propertiesReader.getRoutingKey());
    }

    @Bean
    MessageConverter getMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getMessageConverter());
        return rabbitTemplate;
    }


}
