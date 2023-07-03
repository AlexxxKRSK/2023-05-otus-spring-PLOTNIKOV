package ru.otus.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.otus.domain.converters.QuestionConverter;

@RequiredArgsConstructor
@Configuration
public class ConversionServiceConfig {

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new QuestionConverter());
//    }

    //    private final ConversionServiceFactoryBean conversionServiceFactoryBean;
//
//    private final I18nService i18nService;
//    private final ConversionService conversionService;

    //    @Bean
////    public ConversionServiceFactoryBean conversionService(@Lazy I18nService i18nService) {
//    public ConversionServiceFactoryBean conversionService(I18nService i18nService) {
//        var conversionServiceFactoryBean = new ConversionServiceFactoryBean();
//        conversionServiceFactoryBean.setConverters(
//                Set.of(
//                        new ResultConverter(i18nService),
//                        new QuestionConverter()
//                )
//        );
//        return conversionServiceFactoryBean;
//    }
    @Autowired
    private ConfigurableEnvironment environment;

    @PostConstruct
    public void setConverters() {
        ConfigurableConversionService conS =  environment.getConversionService();
       conS.addConverter(
                new QuestionConverter()
        );
    }

}
