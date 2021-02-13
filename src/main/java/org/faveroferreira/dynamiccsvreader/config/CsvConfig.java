package org.faveroferreira.dynamiccsvreader.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "csv")
public class CsvConfig {

    private Map<String, Integer> propertyOrder;

    private String regex;


}
