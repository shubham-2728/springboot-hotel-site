package ch.hslu.se.hsluhotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main application class for the HSLU Hotel application.
 * This class is responsible for bootstrapping the Spring Boot application.
 */

@SpringBootApplication
public class HsluHotelApplication {

    // Main method to run the Spring Boot application.
    public static void main(String[] args) {
        SpringApplication.run(HsluHotelApplication.class, args);
    }

    // Configures CORS settings for the application.
    // This bean allows all origins, headers, and methods for cross-origin requests
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }

}

//Port 8082
//Hikari-pool = If DB connection started.
//Connection string - jdbc:h2:mem:1
//