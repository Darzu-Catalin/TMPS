package pcstore.models;

/**
 * PremiumCustomer class extends Customer (LSP: Substitutable for Customer)
 * Demonstrates Liskov Substitution Principle - can be used anywhere Customer is expected
 */
public class PremiumCustomer extends Customer {
    private int loyaltyPoints;

    public PremiumCustomer(String customerId, String firstName, String lastName, String email, String phone, String address, int loyaltyPoints) {
        super(customerId, firstName, lastName, email, phone, address);
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public double getDiscountPercentage() {
        return 10.0; // Premium customers get 10% discount
    }

    @Override
    public String getCustomerType() {
        return "Premium Customer";
    }

    @Override
    public boolean isEligibleForFreeShipping(double orderTotal) {
        return orderTotal >= 50.0; // Premium customers get free shipping for orders over $50
    }

    // Premium customer specific behavior
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        loyaltyPoints += points;
    }

    public boolean canRedeemPoints(int pointsToRedeem) {
        return loyaltyPoints >= pointsToRedeem;
    }

    public void redeemPoints(int pointsToRedeem) {
        if (canRedeemPoints(pointsToRedeem)) {
            loyaltyPoints -= pointsToRedeem;
        } else {
            throw new IllegalArgumentException("Insufficient loyalty points");
        }
    }
}