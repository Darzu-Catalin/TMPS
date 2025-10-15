package pcstore.models;

/**
 * Base Customer class (LSP: Base class for substitution)
 * Demonstrates Liskov Substitution Principle
 */
public abstract class Customer {
    protected String customerId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;
    protected String address;

    public Customer(String customerId, String firstName, String lastName, String email, String phone, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Abstract methods for LSP - all subclasses must implement these properly
    public abstract double getDiscountPercentage();
    public abstract String getCustomerType();
    public abstract boolean isEligibleForFreeShipping(double orderTotal);

    // Common behavior for all customers (LSP: inherited behavior)
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getContactInfo() {
        return String.format("Email: %s, Phone: %s", email, phone);
    }

    // Getters (SRP: only data access)
    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    // Setters (SRP: only data modification)
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return String.format("%s{id='%s', name='%s', email='%s'}", 
                           getCustomerType(), customerId, getFullName(), email);
    }
}