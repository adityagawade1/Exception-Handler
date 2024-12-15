package hms.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(GlobalExceptionHandler.class)
@Log4j2
public class GlobalExceptionHandlerAutoConfiguration {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(){
        log.info("No bean found with name: GlobalExceptionHandler, Creating default configuration.." );
        return new GlobalExceptionHandler();
    }
}
