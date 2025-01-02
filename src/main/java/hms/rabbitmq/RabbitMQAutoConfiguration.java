package hms.rabbitmq;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(RabbitMQConfiguration.class)
@Log4j2
public class RabbitMQAutoConfiguration {

    @Bean
    public RabbitMQConfiguration rabbitMQConfiguration(){
        log.info("Bean not found.., creating default rabbit mq configuration");
        return new RabbitMQConfiguration();
    }


}
