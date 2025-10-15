package pcstore.pricing;

import pcstore.models.Customer;
import pcstore.models.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BulkPricingStrategy (OCP: Extension of PricingStrategy)
 * Demonstrates Open/Closed Principle - extends without modifying interface
 */
public class BulkPricingStrategy implements PricingStrategy {
    private static final int BULK_THRESHOLD = 5;
    private static final double BULK_DISCOUNT = 0.05; // 5% additional discount for bulk

    @Override
    public BigDecimal calculatePrice(Product product, Customer customer, int quantity) {
        BigDecimal basePrice = product.getBasePrice();
        BigDecimal totalPrice = basePrice.multiply(BigDecimal.valueOf(quantity));
        
        // Apply customer discount first
        double customerDiscountPercentage = customer.getDiscountPercentage();
        if (customerDiscountPercentage > 0) {
            BigDecimal customerDiscount = totalPrice.multiply(BigDecimal.valueOf(customerDiscountPercentage / 100));
            totalPrice = totalPrice.subtract(customerDiscount);
        }
        
        // Apply bulk discount if quantity qualifies
        if (quantity >= BULK_THRESHOLD) {
            BigDecimal bulkDiscount = totalPrice.multiply(BigDecimal.valueOf(BULK_DISCOUNT));
            totalPrice = totalPrice.subtract(bulkDiscount);
        }
        
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getStrategyName() {
        return "Bulk Pricing";
    }

    @Override
    public String getDescription() {
        return String.format("Regular pricing with additional %.0f%% discount for orders of %d+ items", 
                           BULK_DISCOUNT * 100, BULK_THRESHOLD);
    }
}