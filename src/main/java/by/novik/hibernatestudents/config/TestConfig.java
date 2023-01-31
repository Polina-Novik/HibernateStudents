package by.novik.hibernatedemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "my.test") //класс из аппликатион пропертиес тащит все сюда (в ресурсес) туда можн вставить пароль и тд
public class TestConfig {
 private String value;
 private int number;
 private String parameter;
}
