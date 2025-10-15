package pcstore.repositories;

import pcstore.models.Customer;
import java.util.*;
import java.util.stream.Collectors;

/**
 * CustomerRepository class (SRP: Only responsible for customer data persistence)
 * Demonstrates Single Responsibility Principle
 */
public class CustomerRepository {
    private final Map<String, Customer> customers;

    public CustomerRepository() {
        this.customers = new HashMap<>();
    }

    // Basic CRUD operations (SRP: only data persistence)
    public void save(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Optional<Customer> findById(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public boolean deleteById(String customerId) {
        return customers.remove(customerId) != null;
    }

    public boolean existsById(String customerId) {
        return customers.containsKey(customerId);
    }

    // Query methods (SRP: only data retrieval)
    public Optional<Customer> findByEmail(String email) {
        return customers.values().stream()
                       .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                       .findFirst();
    }

    public List<Customer> findByType(String customerType) {
        return customers.values().stream()
                       .filter(customer -> customer.getCustomerType().equalsIgnoreCase(customerType))
                       .collect(Collectors.toList());
    }

    public List<Customer> searchByName(String nameQuery) {
        String query = nameQuery.toLowerCase();
        return customers.values().stream()
                       .filter(customer -> customer.getFullName().toLowerCase().contains(query))
                       .collect(Collectors.toList());
    }

    public List<Customer> findByPhone(String phone) {
        return customers.values().stream()
                       .filter(customer -> customer.getPhone().equals(phone))
                       .collect(Collectors.toList());
    }

    public int getTotalCount() {
        return customers.size();
    }

    public long getCountByType(String customerType) {
        return customers.values().stream()
                       .filter(customer -> customer.getCustomerType().equalsIgnoreCase(customerType))
                       .count();
    }

    public boolean emailExists(String email) {
        return customers.values().stream()
                       .anyMatch(customer -> customer.getEmail().equalsIgnoreCase(email));
    }
}