package pcstore.models;

/**
 * RegularCustomer class extends Customer (LSP: Substitutable for Customer)
 * Demonstrates Liskov Substitution Principle - can be used anywhere Customer is expected
 */
public class RegularCustomer extends Customer {

    public RegularCustomer(String customerId, String firstName, String lastName, String email, String phone, String address) {
        super(customerId, firstName, lastName, email, phone, address);
    }

    @Override
    public double getDiscountPercentage() {
        return 0.0; // Regular customers get no discount
    }

    @Override
    public String getCustomerType() {
        return "Regular Customer";
    }

    @Override
    public boolean isEligibleForFreeShipping(double orderTotal) {
        return orderTotal >= 100.0; // Free shipping for orders over $100
    }

    // Regular customer specific behavior
    public boolean canUpgradeToPremium(int orderCount) {
        return orderCount >= 5;
    }
}