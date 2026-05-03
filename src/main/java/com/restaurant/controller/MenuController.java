package com.restaurant.controller;

import com.restaurant.model.MenuItem;
import com.restaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    
    @Autowired
    private MenuRepository menuRepository;
    
    // Initialize some sample data
    @PostConstruct
    public void init() {
        if (menuRepository.count() == 0) {
            // Add sample data if database is empty
            menuRepository.saveAll(List.of(
                new MenuItem("Margherita Pizza", "Classic tomato and mozzarella pizza with fresh basil", 
                           12.99, "Pizza", true, "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400"),
                new MenuItem("Pepperoni Pizza", "Spicy pepperoni with extra cheese", 
                           14.99, "Pizza", false, "https://images.unsplash.com/photo-1628840042765-356cda07504e?w=400"),
                new MenuItem("BBQ Chicken Pizza", "Grilled chicken with BBQ sauce and onions", 
                           15.99, "Pizza", false, "https://images.unsplash.com/photo-1595708684082-a173bb3a06c5?w=400"),
                new MenuItem("Caesar Salad", "Fresh romaine lettuce with Caesar dressing and croutons", 
                           8.99, "Salad", true, "https://images.unsplash.com/photo-1546793665-c74683f339c1?w=400"),
                new MenuItem("Greek Salad", "Tomatoes, cucumbers, onions, feta cheese, olives", 
                           9.99, "Salad", true, "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=400"),
                new MenuItem("Classic Burger", "Beef patty with lettuce, tomato, and special sauce", 
                           10.99, "Burger", false, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400"),
                new MenuItem("Veggie Burger", "Plant-based patty with avocado and sprouts", 
                           9.99, "Burger", true, "https://images.unsplash.com/photo-1586190848861-99aa4a171e90?w=400"),
                new MenuItem("Chicken Burger", "Grilled chicken breast with mayo and lettuce", 
                           11.99, "Burger", false, "https://images.unsplash.com/photo-1521390188846-ea6b5d21b8d3?w=400"),
                new MenuItem("Chocolate Lava Cake", "Warm chocolate cake with molten center, served with ice cream", 
                           6.99, "Dessert", true, "https://images.unsplash.com/photo-1624353365286-3f8d62daad51?w=400"),
                new MenuItem("Tiramisu", "Classic Italian dessert with coffee and mascarpone", 
                           7.99, "Dessert", true, "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=400"),
                new MenuItem("Mango Smoothie", "Fresh mango blended with yogurt and honey", 
                           5.99, "Drinks", true, "https://images.unsplash.com/photo-1628992682633-bf2d40cb595f?w=400"),
                new MenuItem("Fresh Lemonade", "Homemade lemonade with mint", 
                           4.99, "Drinks", true, "https://images.unsplash.com/photo-1621506289937-a8e4df240d0b?w=400"),
                new MenuItem("Iced Coffee", "Cold brew coffee with milk and vanilla syrup", 
                           5.49, "Drinks", true, "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=400"),
                new MenuItem("Spaghetti Carbonara", "Pasta with eggs, cheese, pancetta, and black pepper", 
                           13.99, "Pasta", false, "https://images.unsplash.com/photo-1598866594230-a7c12756260f?w=400"),
                new MenuItem("Vegetable Pasta", "Pasta with mixed vegetables in tomato sauce", 
                           12.99, "Pasta", true, "https://images.unsplash.com/photo-1563379926898-05f4575a45d8?w=400")
            ));
        }
    }
    
    // Home page
    @GetMapping("/")
    public String home(Model model) {
        List<MenuItem> items = menuRepository.findAll();
        List<String> categories = menuRepository.findAllCategories();
        
        model.addAttribute("menuItems", items);
        model.addAttribute("categories", categories);
        model.addAttribute("totalItems", items.size());
        
        return "index";
    }
    
    // Get all items (API)
    @GetMapping("/all")
    @ResponseBody
    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }
    
    // Get item by ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
        return menuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Get by category
    @GetMapping("/category/{category}")
    @ResponseBody
    public List<MenuItem> getByCategory(@PathVariable String category) {
        return menuRepository.findByCategory(category);
    }
    
    // Search items
    @GetMapping("/search")
    @ResponseBody
    public List<MenuItem> searchItems(@RequestParam String query) {
        return menuRepository.findByNameContainingIgnoreCase(query);
    }
    
    // Get vegetarian items
    @GetMapping("/vegetarian")
    @ResponseBody
    public List<MenuItem> getVegetarianItems() {
        return menuRepository.findByVegetarian(true);
    }
    // Add new item
@PostMapping("/add")
@ResponseBody
public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
    try {
        System.out.println("Adding menu item: " + menuItem.getName());
        System.out.println("Category: " + menuItem.getCategory());
        System.out.println("Vegetarian: " + menuItem.isVegetarian());
        
        MenuItem savedItem = menuRepository.save(menuItem);
        System.out.println("Item saved with ID: " + savedItem.getId());
        
        return ResponseEntity.ok(savedItem);
    } catch (Exception e) {
        System.out.println("Error saving item: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(500).build();
    }
}
    // Update item
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, 
                                                   @RequestBody MenuItem menuItem) {
        if (!menuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        menuItem.setId(id);
        return ResponseEntity.ok(menuRepository.save(menuItem));
    }
    
    // Delete item
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        if (!menuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        menuRepository.deleteById(id);
        return ResponseEntity.ok("Item deleted successfully");
    }
    
    // Get statistics
    @GetMapping("/stats")
    @ResponseBody
    public ResponseEntity<?> getStats() {
        long totalItems = menuRepository.count();
        long vegetarianCount = menuRepository.findByVegetarian(true).size();
        List<String> categories = menuRepository.findAllCategories();
        
        // Create a simple object to return as JSON
        StatsResponse stats = new StatsResponse(totalItems, vegetarianCount, categories);
        return ResponseEntity.ok(stats);
    }
    
    // Inner class for stats response
    private static class StatsResponse {
        private long totalItems;
        private long vegetarianCount;
        private List<String> categories;
        
        public StatsResponse(long totalItems, long vegetarianCount, List<String> categories) {
            this.totalItems = totalItems;
            this.vegetarianCount = vegetarianCount;
            this.categories = categories;
        }
        
        // Getters
        public long getTotalItems() { return totalItems; }
        public long getVegetarianCount() { return vegetarianCount; }
        public List<String> getCategories() { return categories; }
    }
}