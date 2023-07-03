package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import ru.otus.domain.converters.QuestionConverter;
import ru.otus.domain.converters.ResultConverter;

import java.util.Set;

@Configuration
public class ConversionServiceConfig {

    @Bean
    public ConversionServiceFactoryBean conversionService() {

        var conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(
                Set.of(
                        new ResultConverter(),
                        new QuestionConverter()
                )
        );
        return conversionServiceFactoryBean;
    }
}
