package pcstore.shipping;

import pcstore.models.Customer;
import pcstore.models.PremiumCustomer;
import pcstore.models.CorporateCustomer;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ExpressShipping (OCP & LSP: Extension that can substitute interface)
 * Demonstrates Open/Closed Principle and Liskov Substitution Principle
 */
public class ExpressShipping implements ShippingCalculator {
    private static final double RATE_PER_KG = 12.0;
    private static final double BASE_COST = 25.0;
    private static final double PREMIUM_DISCOUNT = 0.20; // 20% discount for premium customers

    @Override
    public BigDecimal calculateShippingCost(Customer customer, double orderTotal, double weight) {
        // Corporate customers always get free express shipping
        if (customer instanceof CorporateCustomer) {
            return BigDecimal.ZERO;
        }
        
        double cost = BASE_COST + (weight * RATE_PER_KG);
        
        // Premium customers get discount on express shipping
        if (customer instanceof PremiumCustomer) {
            cost *= (1.0 - PREMIUM_DISCOUNT);
        }
        
        return BigDecimal.valueOf(cost).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getShippingMethod() {
        return "Express Shipping";
    }

    @Override
    public int getEstimatedDeliveryDays() {
        return 2; // 1-2 business days
    }

    @Override
    public boolean isAvailableForCustomer(Customer customer) {
        // Express shipping available for Premium and Corporate customers
        return customer instanceof PremiumCustomer || customer instanceof CorporateCustomer;
    }
}