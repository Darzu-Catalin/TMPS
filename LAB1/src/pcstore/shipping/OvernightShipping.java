package pcstore.shipping;

import pcstore.models.Customer;
import pcstore.models.CorporateCustomer;
import java.math.BigDecimal;

/**
 * OvernightShipping (OCP & LSP: Extension that can substitute interface)
 * Demonstrates Open/Closed Principle and Liskov Substitution Principle
 */
public class OvernightShipping implements ShippingCalculator {
    private static final double FLAT_RATE = 50.0;

    @Override
    public BigDecimal calculateShippingCost(Customer customer, double orderTotal, double weight) {
        // Only corporate customers with high order values get free overnight shipping
        if (customer instanceof CorporateCustomer && orderTotal >= 1000.0) {
            return BigDecimal.ZERO;
        }
        
        return BigDecimal.valueOf(FLAT_RATE);
    }

    @Override
    public String getShippingMethod() {
        return "Overnight Shipping";
    }

    @Override
    public int getEstimatedDeliveryDays() {
        return 1; // Next business day
    }

    @Override
    public boolean isAvailableForCustomer(Customer customer) {
        // Overnight shipping only available for Corporate customers
        return customer instanceof CorporateCustomer;
    }
}