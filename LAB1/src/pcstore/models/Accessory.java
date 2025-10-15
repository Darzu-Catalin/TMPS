package pcstore.models;

import java.math.BigDecimal;

/**
 * Accessory class extends Product (OCP: Open for extension)
 * Demonstrates Open/Closed Principle - extends without modifying base Product
 */
public class Accessory extends Product {
    private String accessoryType;
    private String compatibility;
    private String color;
    private boolean isWireless;

    public Accessory(String productId, String name, String brand, BigDecimal basePrice, int stockQuantity, 
                     String description, String accessoryType, String compatibility, String color, boolean isWireless) {
        super(productId, name, brand, basePrice, stockQuantity, description);
        this.accessoryType = accessoryType;
        this.compatibility = compatibility;
        this.color = color;
        this.isWireless = isWireless;
    }

    @Override
    public String getCategory() {
        return "Accessory";
    }

    @Override
    public String getSpecifications() {
        return String.format("Type: %s, Compatibility: %s, Color: %s, Wireless: %s", 
                           accessoryType, compatibility, color, isWireless ? "Yes" : "No");
    }

    // Accessory-specific getters
    public String getAccessoryType() { return accessoryType; }
    public String getCompatibility() { return compatibility; }
    public String getColor() { return color; }
    public boolean isWireless() { return isWireless; }

    // Accessory-specific setters
    public void setAccessoryType(String accessoryType) { this.accessoryType = accessoryType; }
    public void setCompatibility(String compatibility) { this.compatibility = compatibility; }
    public void setColor(String color) { this.color = color; }
    public void setWireless(boolean wireless) { isWireless = wireless; }
}