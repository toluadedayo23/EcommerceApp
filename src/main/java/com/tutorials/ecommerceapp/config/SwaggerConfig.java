package com.tutorials.ecommerceapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${app.description}") String appDescription,
                                 @Value("${app.version}") String appVersion) {

        return new OpenAPI().info(new Info()
                .contact(new Contact().name("Lut").url("http://lut.com").email("contact.lut@gmail.com"))
                .title("Ecommerce API")
                .version(appVersion)
                .description(appDescription).termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0")));

    }
}
