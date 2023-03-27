package com.yupi.apiclientsdk;

import com.yupi.apiclientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configurable
@ConfigurationProperties(prefix = "api.client")
@ComponentScan
@Data
public class ApiConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public ApiClient apiClient() {
        return new ApiClient(accessKey, secretKey);
    }
}
