package ru.otus.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.otus.domain.converters.QuestionConverter;
import ru.otus.domain.converters.ResultConverter;
import ru.otus.service.I18nService;

@RequiredArgsConstructor
@Configuration
public class ConversionServiceConfig {

    @Bean
    public ConfigurableConversionService configurableConversionService(
            ConfigurableEnvironment environment, I18nService i18nService) {
        ConfigurableConversionService conS = environment.getConversionService();
        conS.addConverter(new ResultConverter(i18nService));
        conS.addConverter(new QuestionConverter());
        return conS;
    }

}
