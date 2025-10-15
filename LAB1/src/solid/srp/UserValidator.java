package solid.srp;

import java.util.regex.Pattern;

/**
 * UserValidator class - Responsible only for validating user data
 * Single Responsibility: User data validation
 */
public class UserValidator {
    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public boolean validateUser(User user) {
        return validateName(user.getName()) && 
               validateEmail(user.getEmail()) && 
               validateId(user.getId());
    }

    private boolean validateName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }

    private boolean validateEmail(String email) {
        return email != null && pattern.matcher(email).matches();
    }

    private boolean validateId(String id) {
        return id != null && !id.trim().isEmpty();
    }

    public String getValidationErrors(User user) {
        StringBuilder errors = new StringBuilder();
        
        if (!validateName(user.getName())) {
            errors.append("Invalid name: must be at least 2 characters. ");
        }
        if (!validateEmail(user.getEmail())) {
            errors.append("Invalid email format. ");
        }
        if (!validateId(user.getId())) {
            errors.append("Invalid ID: cannot be empty. ");
        }
        
        return errors.toString();
    }
}