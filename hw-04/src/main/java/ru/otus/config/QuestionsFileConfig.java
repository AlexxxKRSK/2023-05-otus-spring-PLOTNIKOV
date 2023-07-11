package ru.otus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "file")
@Configuration
public class QuestionsFileConfig {
    private Map<String, String> languageToName;
}
