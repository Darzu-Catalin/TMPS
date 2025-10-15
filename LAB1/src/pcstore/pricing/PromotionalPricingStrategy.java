package pcstore.pricing;

import pcstore.models.Customer;
import pcstore.models.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * PromotionalPricingStrategy (OCP: Extension of PricingStrategy)
 * Demonstrates Open/Closed Principle - extends without modifying interface
 */
public class PromotionalPricingStrategy implements PricingStrategy {
    private final double promotionalDiscountPercentage;
    private final String promotionName;

    public PromotionalPricingStrategy(double promotionalDiscountPercentage, String promotionName) {
        this.promotionalDiscountPercentage = promotionalDiscountPercentage;
        this.promotionName = promotionName;
    }

    @Override
    public BigDecimal calculatePrice(Product product, Customer customer, int quantity) {
        BigDecimal basePrice = product.getBasePrice();
        BigDecimal totalPrice = basePrice.multiply(BigDecimal.valueOf(quantity));
        
        // Apply promotional discount first (typically the best deal)
        BigDecimal promotionalDiscount = totalPrice.multiply(BigDecimal.valueOf(promotionalDiscountPercentage / 100));
        totalPrice = totalPrice.subtract(promotionalDiscount);
        
        // Apply customer discount on the already discounted price
        double customerDiscountPercentage = customer.getDiscountPercentage();
        if (customerDiscountPercentage > 0) {
            BigDecimal customerDiscount = totalPrice.multiply(BigDecimal.valueOf(customerDiscountPercentage / 100));
            totalPrice = totalPrice.subtract(customerDiscount);
        }
        
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getStrategyName() {
        return "Promotional Pricing - " + promotionName;
    }

    @Override
    public String getDescription() {
        return String.format("Special promotion '%s' with %.0f%% discount plus customer discounts", 
                           promotionName, promotionalDiscountPercentage);
    }

    public double getPromotionalDiscountPercentage() {
        return promotionalDiscountPercentage;
    }

    public String getPromotionName() {
        return promotionName;
    }
}