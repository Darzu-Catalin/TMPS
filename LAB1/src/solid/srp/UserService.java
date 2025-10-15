package solid.srp;

/**
 * UserService class - Responsible only for business logic coordination
 * Single Responsibility: Coordinating user-related business operations
 */
public class UserService {
    private UserRepository userRepository;
    private UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public boolean createUser(String id, String name, String email) {
        User user = new User(id, name, email);
        
        if (!userValidator.validateUser(user)) {
            System.out.println("User validation failed: " + userValidator.getValidationErrors(user));
            return false;
        }

        // Check if user already exists
        if (userRepository.findById(id).isPresent()) {
            System.out.println("User with ID " + id + " already exists");
            return false;
        }

        userRepository.save(user);
        System.out.println("User created successfully: " + user);
        return true;
    }

    public boolean updateUser(String id, String name, String email) {
        User updatedUser = new User(id, name, email);
        
        if (!userValidator.validateUser(updatedUser)) {
            System.out.println("User validation failed: " + userValidator.getValidationErrors(updatedUser));
            return false;
        }

        if (userRepository.updateUser(updatedUser)) {
            System.out.println("User updated successfully: " + updatedUser);
            return true;
        } else {
            System.out.println("User with ID " + id + " not found");
            return false;
        }
    }

    public void listAllUsers() {
        var users = userRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("No users found");
        } else {
            System.out.println("All users:");
            users.forEach(System.out::println);
        }
    }
}