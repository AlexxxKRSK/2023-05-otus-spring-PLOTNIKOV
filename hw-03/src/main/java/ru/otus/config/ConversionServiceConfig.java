package ru.otus.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.otus.domain.converters.QuestionConverter;
import ru.otus.domain.converters.ResultConverter;
import ru.otus.utis.LocalizedMessageProvider;

@RequiredArgsConstructor
@Configuration
public class ConversionServiceConfig {

    @Bean
    public ConfigurableConversionService configurableConversionService(
            ConfigurableEnvironment environment, LocalizedMessageProvider localizedMessageProvider) {
        ConfigurableConversionService conS = environment.getConversionService();
        conS.addConverter(new ResultConverter(localizedMessageProvider));
        conS.addConverter(new QuestionConverter());
        return conS;
    }

}
