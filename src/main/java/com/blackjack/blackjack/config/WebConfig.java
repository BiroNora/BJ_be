package com.blackjack.blackjack.config;package com.blackjack.blackjack.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${APP_CORS_ALLOWED_ORIGINS:https://bjjava.vercel.app}")
    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] finalOrigins = (allowedOrigins.length > 0 && allowedOrigins[0].equals("*"))
            ? new String[]{"https://bjjava.vercel.app"}
            : allowedOrigins;

        registry.addMapping("/api/**")
            .allowedOrigins(finalOrigins)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);
    }
}
