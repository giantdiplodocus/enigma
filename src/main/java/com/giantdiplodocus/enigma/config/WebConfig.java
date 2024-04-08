package com.giantdiplodocus.enigma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Configures Cross-Origin Resource Sharing (CORS) mappings to allow requests from
     * a specific origin (http://localhost:3000), with only POST method permitted
     *
     * @param registry the CorsRegistry instance to configure CORS mappings
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("POST");
    }
}
