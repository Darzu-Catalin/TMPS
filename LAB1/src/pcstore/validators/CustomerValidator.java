package pcstore.validators;

import pcstore.models.Customer;
import java.util.regex.Pattern;

/**
 * CustomerValidator class (SRP: Only responsible for customer validation)
 * Demonstrates Single Responsibility Principle
 */
public class CustomerValidator {
    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final String PHONE_PATTERN = "^[+]?[0-9]{10,15}$";
    
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    public boolean validateCustomer(Customer customer) {
        return validateId(customer.getCustomerId()) &&
               validateName(customer.getFirstName()) &&
               validateName(customer.getLastName()) &&
               validateEmail(customer.getEmail()) &&
               validatePhone(customer.getPhone()) &&
               validateAddress(customer.getAddress());
    }

    private boolean validateId(String id) {
        return id != null && !id.trim().isEmpty() && id.length() >= 3;
    }

    private boolean validateName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }

    private boolean validateEmail(String email) {
        return email != null && emailPattern.matcher(email).matches();
    }

    private boolean validatePhone(String phone) {
        return phone != null && phonePattern.matcher(phone.replaceAll("\\s|-", "")).matches();
    }

    private boolean validateAddress(String address) {
        return address != null && !address.trim().isEmpty() && address.length() >= 10;
    }

    public String getValidationErrors(Customer customer) {
        StringBuilder errors = new StringBuilder();
        
        if (!validateId(customer.getCustomerId())) {
            errors.append("Invalid customer ID: must be at least 3 characters. ");
        }
        if (!validateName(customer.getFirstName())) {
            errors.append("Invalid first name: must be at least 2 characters. ");
        }
        if (!validateName(customer.getLastName())) {
            errors.append("Invalid last name: must be at least 2 characters. ");
        }
        if (!validateEmail(customer.getEmail())) {
            errors.append("Invalid email format. ");
        }
        if (!validatePhone(customer.getPhone())) {
            errors.append("Invalid phone number format. ");
        }
        if (!validateAddress(customer.getAddress())) {
            errors.append("Invalid address: must be at least 10 characters. ");
        }
        
        return errors.toString();
    }

    public boolean isEmailUnique(String email, java.util.List<Customer> existingCustomers) {
        return existingCustomers.stream()
                               .noneMatch(customer -> customer.getEmail().equalsIgnoreCase(email));
    }
}