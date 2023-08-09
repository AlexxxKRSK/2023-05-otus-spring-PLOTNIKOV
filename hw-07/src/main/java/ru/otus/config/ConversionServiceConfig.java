package ru.otus.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.otus.dto.converters.BookDtoConverter;
import ru.otus.dto.converters.CommentDtoConverter;

@RequiredArgsConstructor
@Configuration
public class ConversionServiceConfig {

    @Bean
    public ConfigurableConversionService configurableConversionService(ConfigurableEnvironment environment) {
        ConfigurableConversionService conS = environment.getConversionService();
        conS.addConverter(new BookDtoConverter());
        conS.addConverter(new CommentDtoConverter());
        return conS;
    }

}
