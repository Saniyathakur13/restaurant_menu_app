package com.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.restaurant.model")
@EnableJpaRepositories("com.restaurant.repository")
public class RestaurantApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
        System.out.println("\n=========================================");
        System.out.println("🍽️  Restaurant Menu App Started!");
        System.out.println("🌐 Open: http://localhost:8080/menu/");
        System.out.println("📊 Database: MySQL (restaurant_db)");
        System.out.println("=========================================\n");
    }
}