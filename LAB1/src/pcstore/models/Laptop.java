package pcstore.models;

import java.math.BigDecimal;

/**
 * Laptop class extends Product (OCP: Open for extension)
 * Demonstrates Open/Closed Principle - extends without modifying base Product
 */
public class Laptop extends Product {
    private String processor;
    private int ramGB;
    private int storageGB;
    private String screenSize;
    private String operatingSystem;

    public Laptop(String productId, String name, String brand, BigDecimal basePrice, int stockQuantity, 
                  String description, String processor, int ramGB, int storageGB, String screenSize, String operatingSystem) {
        super(productId, name, brand, basePrice, stockQuantity, description);
        this.processor = processor;
        this.ramGB = ramGB;
        this.storageGB = storageGB;
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String getCategory() {
        return "Laptop";
    }

    @Override
    public String getSpecifications() {
        return String.format("Processor: %s, RAM: %dGB, Storage: %dGB, Screen: %s, OS: %s", 
                           processor, ramGB, storageGB, screenSize, operatingSystem);
    }

    // Laptop-specific getters
    public String getProcessor() { return processor; }
    public int getRamGB() { return ramGB; }
    public int getStorageGB() { return storageGB; }
    public String getScreenSize() { return screenSize; }
    public String getOperatingSystem() { return operatingSystem; }

    // Laptop-specific setters
    public void setProcessor(String processor) { this.processor = processor; }
    public void setRamGB(int ramGB) { this.ramGB = ramGB; }
    public void setStorageGB(int storageGB) { this.storageGB = storageGB; }
    public void setScreenSize(String screenSize) { this.screenSize = screenSize; }
    public void setOperatingSystem(String operatingSystem) { this.operatingSystem = operatingSystem; }
}