package pcstore.services;

import pcstore.models.Customer;
import pcstore.repositories.CustomerRepository;
import pcstore.validators.CustomerValidator;
import java.util.List;
import java.util.Optional;

/**
 * CustomerService (SRP: Only responsible for customer business logic)
 * Demonstrates Single Responsibility Principle
 */
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;

    public CustomerService(CustomerRepository customerRepository, CustomerValidator customerValidator) {
        this.customerRepository = customerRepository;
        this.customerValidator = customerValidator;
    }

    public boolean registerCustomer(Customer customer) {
        if (!customerValidator.validateCustomer(customer)) {
            System.out.println("❌ Customer validation failed: " + customerValidator.getValidationErrors(customer));
            return false;
        }

        if (customerRepository.existsById(customer.getCustomerId())) {
            System.out.println("❌ Customer with ID " + customer.getCustomerId() + " already exists");
            return false;
        }

        if (customerRepository.emailExists(customer.getEmail())) {
            System.out.println("❌ Email " + customer.getEmail() + " is already registered");
            return false;
        }

        customerRepository.save(customer);
        System.out.println("✅ Customer registered successfully: " + customer);
        return true;
    }

    public Optional<Customer> getCustomer(String customerId) {
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersByType(String customerType) {
        return customerRepository.findByType(customerType);
    }

    public List<Customer> searchCustomers(String nameQuery) {
        return customerRepository.searchByName(nameQuery);
    }

    public boolean updateCustomerInfo(String customerId, String firstName, String lastName, String email, String phone, String address) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            
            // Check if new email is unique (if different from current)
            if (!customer.getEmail().equals(email) && customerRepository.emailExists(email)) {
                System.out.println("❌ Email " + email + " is already registered to another customer");
                return false;
            }
            
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setAddress(address);
            
            if (customerValidator.validateCustomer(customer)) {
                customerRepository.save(customer);
                System.out.println("✅ Customer information updated: " + customer.getFullName());
                return true;
            } else {
                System.out.println("❌ Updated customer information is invalid: " + customerValidator.getValidationErrors(customer));
                return false;
            }
        }
        System.out.println("❌ Customer not found: " + customerId);
        return false;
    }

    public void displayCustomerReport() {
        List<Customer> allCustomers = customerRepository.findAll();
        
        System.out.println("👥 CUSTOMER REPORT");
        System.out.println("=".repeat(40));
        System.out.println("Total Customers: " + allCustomers.size());
        
        System.out.println("\n📊 Customers by Type:");
        allCustomers.stream()
                   .collect(java.util.stream.Collectors.groupingBy(Customer::getCustomerType))
                   .forEach((type, customers) -> 
                       System.out.println("  " + type + ": " + customers.size() + " customers"));
        
        System.out.println("\n🏆 Customer Benefits by Type:");
        System.out.println("  Regular Customers: 0% discount, free shipping over $100");
        System.out.println("  Premium Customers: 10% discount, free shipping over $50");
        System.out.println("  Corporate Customers: 15% discount, always free shipping");
    }

    public void displayCustomerDetails(String customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            System.out.println("👤 CUSTOMER DETAILS");
            System.out.println("=".repeat(30));
            System.out.println("ID: " + customer.getCustomerId());
            System.out.println("Name: " + customer.getFullName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone: " + customer.getPhone());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Type: " + customer.getCustomerType());
            System.out.println("Discount: " + customer.getDiscountPercentage() + "%");
            System.out.println("Free Shipping Threshold: $" + (customer.isEligibleForFreeShipping(100) ? "100" : customer.isEligibleForFreeShipping(50) ? "50" : "Never"));
        } else {
            System.out.println("❌ Customer not found: " + customerId);
        }
    }
}