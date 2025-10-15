package pcstore.pricing;

import pcstore.models.Customer;
import pcstore.models.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * RegularPricingStrategy (OCP: Extension of PricingStrategy)
 * Demonstrates Open/Closed Principle - extends without modifying interface
 */
public class RegularPricingStrategy implements PricingStrategy {

    @Override
    public BigDecimal calculatePrice(Product product, Customer customer, int quantity) {
        BigDecimal basePrice = product.getBasePrice();
        BigDecimal totalPrice = basePrice.multiply(BigDecimal.valueOf(quantity));
        
        // Apply customer discount
        double discountPercentage = customer.getDiscountPercentage();
        if (discountPercentage > 0) {
            BigDecimal discount = totalPrice.multiply(BigDecimal.valueOf(discountPercentage / 100));
            totalPrice = totalPrice.subtract(discount);
        }
        
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getStrategyName() {
        return "Regular Pricing";
    }

    @Override
    public String getDescription() {
        return "Standard pricing with customer-specific discounts applied";
    }
}