package com.restaurant.repository;

import com.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    
    // Find by category
    List<MenuItem> findByCategory(String category);
    
    // Find vegetarian items
    List<MenuItem> findByVegetarian(boolean vegetarian);
    
    // Find by price range
    List<MenuItem> findByPriceBetween(double minPrice, double maxPrice);
    
    // Search by name (case insensitive)
    List<MenuItem> findByNameContainingIgnoreCase(String name);
    
    // Find by multiple categories
    @Query("SELECT m FROM MenuItem m WHERE m.category IN :categories")
    List<MenuItem> findByCategories(@Param("categories") List<String> categories);
    
    // Get all distinct categories
    @Query("SELECT DISTINCT m.category FROM MenuItem m")
    List<String> findAllCategories();
}