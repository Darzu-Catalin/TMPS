package pcstore.shipping;

import pcstore.models.Customer;
import java.math.BigDecimal;

/**
 * ShippingCalculator interface (OCP: Closed for modification, open for extension)
 * Demonstrates Open/Closed Principle
 */
public interface ShippingCalculator {
    BigDecimal calculateShippingCost(Customer customer, double orderTotal, double weight);
    String getShippingMethod();
    int getEstimatedDeliveryDays();
    boolean isAvailableForCustomer(Customer customer);
}