package by.novik.hibernatestudents.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "my.test")

public class TestConfig {
    private String value;
    private int number;
    private String parameter;
}
