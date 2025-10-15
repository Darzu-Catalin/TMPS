package pcstore.models;

import java.math.BigDecimal;

/**
 * Base Product class (SRP: Only manages product data)
 * This class demonstrates Single Responsibility Principle
 */
public abstract class Product {
    protected String productId;
    protected String name;
    protected String brand;
    protected BigDecimal basePrice;
    protected int stockQuantity;
    protected String description;

    public Product(String productId, String name, String brand, BigDecimal basePrice, int stockQuantity, String description) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.basePrice = basePrice;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    // Abstract method for OCP - allows extension without modification
    public abstract String getCategory();
    public abstract String getSpecifications();

    // Getters (SRP: only data access)
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public BigDecimal getBasePrice() { return basePrice; }
    public int getStockQuantity() { return stockQuantity; }
    public String getDescription() { return description; }

    // Setters (SRP: only data modification)
    public void setName(String name) { this.name = name; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setBasePrice(BigDecimal basePrice) { this.basePrice = basePrice; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setDescription(String description) { this.description = description; }

    public void reduceStock(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity;
        } else {
            throw new IllegalArgumentException("Insufficient stock");
        }
    }

    public void addStock(int quantity) {
        stockQuantity += quantity;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    @Override
    public String toString() {
        return String.format("%s{id='%s', name='%s', brand='%s', price=$%.2f, stock=%d}", 
                           getClass().getSimpleName(), productId, name, brand, basePrice, stockQuantity);
    }
}