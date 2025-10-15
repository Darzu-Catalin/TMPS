package pcstore.models;

/**
 * CorporateCustomer class extends Customer (LSP: Substitutable for Customer)
 * Demonstrates Liskov Substitution Principle - can be used anywhere Customer is expected
 */
public class CorporateCustomer extends Customer {
    private String companyName;
    private String taxId;
    private double creditLimit;
    private double currentCredit;

    public CorporateCustomer(String customerId, String firstName, String lastName, String email, String phone, 
                           String address, String companyName, String taxId, double creditLimit) {
        super(customerId, firstName, lastName, email, phone, address);
        this.companyName = companyName;
        this.taxId = taxId;
        this.creditLimit = creditLimit;
        this.currentCredit = 0.0;
    }

    @Override
    public double getDiscountPercentage() {
        return 15.0; // Corporate customers get 15% discount
    }

    @Override
    public String getCustomerType() {
        return "Corporate Customer";
    }

    @Override
    public boolean isEligibleForFreeShipping(double orderTotal) {
        return true; // Corporate customers always get free shipping
    }

    // Corporate customer specific behavior
    public String getCompanyName() {
        return companyName;
    }

    public String getTaxId() {
        return taxId;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getCurrentCredit() {
        return currentCredit;
    }

    public double getAvailableCredit() {
        return creditLimit - currentCredit;
    }

    public boolean canPurchaseOnCredit(double amount) {
        return getAvailableCredit() >= amount;
    }

    public void addCredit(double amount) {
        if (currentCredit + amount <= creditLimit) {
            currentCredit += amount;
        } else {
            throw new IllegalArgumentException("Credit limit exceeded");
        }
    }

    public void payCredit(double amount) {
        if (amount <= currentCredit) {
            currentCredit -= amount;
        } else {
            throw new IllegalArgumentException("Payment amount exceeds current credit");
        }
    }

    @Override
    public String toString() {
        return String.format("%s{id='%s', company='%s', contact='%s', email='%s'}", 
                           getCustomerType(), customerId, companyName, getFullName(), email);
    }
}