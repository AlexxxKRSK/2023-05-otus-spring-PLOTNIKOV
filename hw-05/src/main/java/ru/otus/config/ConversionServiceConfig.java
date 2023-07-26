package ru.otus.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.otus.domain.converters.AuthorConverter;
import ru.otus.domain.converters.BookConverter;
import ru.otus.domain.converters.GenreConverter;

@RequiredArgsConstructor
@Configuration
public class ConversionServiceConfig {

    @Bean
    public ConfigurableConversionService configurableConversionService(ConfigurableEnvironment environment) {
        ConfigurableConversionService conS = environment.getConversionService();
        conS.addConverter(new BookConverter());
        conS.addConverter(new AuthorConverter());
        conS.addConverter(new GenreConverter());
        return conS;
    }

}
