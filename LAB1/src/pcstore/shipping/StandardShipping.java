package pcstore.shipping;

import pcstore.models.Customer;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * StandardShipping (OCP & LSP: Extension that can substitute interface)
 * Demonstrates Open/Closed Principle and Liskov Substitution Principle
 */
public class StandardShipping implements ShippingCalculator {
    private static final double RATE_PER_KG = 5.0;
    private static final double BASE_COST = 10.0;

    @Override
    public BigDecimal calculateShippingCost(Customer customer, double orderTotal, double weight) {
        // Check if customer is eligible for free shipping
        if (customer.isEligibleForFreeShipping(orderTotal)) {
            return BigDecimal.ZERO;
        }
        
        double cost = BASE_COST + (weight * RATE_PER_KG);
        return BigDecimal.valueOf(cost).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getShippingMethod() {
        return "Standard Shipping";
    }

    @Override
    public int getEstimatedDeliveryDays() {
        return 5; // 3-5 business days
    }

    @Override
    public boolean isAvailableForCustomer(Customer customer) {
        return true; // Available for all customers
    }
}