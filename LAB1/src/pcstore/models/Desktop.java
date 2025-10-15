package pcstore.models;

import java.math.BigDecimal;

/**
 * Desktop class extends Product (OCP: Open for extension)
 * Demonstrates Open/Closed Principle - extends without modifying base Product
 */
public class Desktop extends Product {
    private String processor;
    private int ramGB;
    private int storageGB;
    private String graphicsCard;
    private String motherboard;
    private boolean hasWiFi;

    public Desktop(String productId, String name, String brand, BigDecimal basePrice, int stockQuantity, 
                   String description, String processor, int ramGB, int storageGB, String graphicsCard, 
                   String motherboard, boolean hasWiFi) {
        super(productId, name, brand, basePrice, stockQuantity, description);
        this.processor = processor;
        this.ramGB = ramGB;
        this.storageGB = storageGB;
        this.graphicsCard = graphicsCard;
        this.motherboard = motherboard;
        this.hasWiFi = hasWiFi;
    }

    @Override
    public String getCategory() {
        return "Desktop";
    }

    @Override
    public String getSpecifications() {
        return String.format("Processor: %s, RAM: %dGB, Storage: %dGB, GPU: %s, Motherboard: %s, WiFi: %s", 
                           processor, ramGB, storageGB, graphicsCard, motherboard, hasWiFi ? "Yes" : "No");
    }

    // Desktop-specific getters
    public String getProcessor() { return processor; }
    public int getRamGB() { return ramGB; }
    public int getStorageGB() { return storageGB; }
    public String getGraphicsCard() { return graphicsCard; }
    public String getMotherboard() { return motherboard; }
    public boolean hasWiFi() { return hasWiFi; }

    // Desktop-specific setters
    public void setProcessor(String processor) { this.processor = processor; }
    public void setRamGB(int ramGB) { this.ramGB = ramGB; }
    public void setStorageGB(int storageGB) { this.storageGB = storageGB; }
    public void setGraphicsCard(String graphicsCard) { this.graphicsCard = graphicsCard; }
    public void setMotherboard(String motherboard) { this.motherboard = motherboard; }
    public void setHasWiFi(boolean hasWiFi) { this.hasWiFi = hasWiFi; }
}