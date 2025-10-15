package pcstore.pricing;

import pcstore.models.Customer;
import pcstore.models.Product;
import java.math.BigDecimal;

/**
 * PricingStrategy interface (OCP: Closed for modification, open for extension)
 * Demonstrates Open/Closed Principle
 */
public interface PricingStrategy {
    BigDecimal calculatePrice(Product product, Customer customer, int quantity);
    String getStrategyName();
    String getDescription();
}