package xyz.leefly.project.web.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(value = "admin")
@PropertySource(value = {"classpath:config/config.properties"})
public class WebConfigurer {

    private String name;
    private String password;

}
