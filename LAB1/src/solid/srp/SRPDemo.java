package solid.srp;

/**
 * Standalone demonstration of Single Responsibility Principle (SRP)
 * Run this class to see SRP in action
 */
public class SRPDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("SINGLE RESPONSIBILITY PRINCIPLE (SRP) DEMONSTRATION");
        System.out.println("=".repeat(60));
        System.out.println("Principle: A class should have only one reason to change.");
        System.out.println("Each class in this demo has a single, well-defined responsibility.\n");

        // Create dependencies - each with a single responsibility
        UserRepository userRepository = new UserRepository();
        UserValidator userValidator = new UserValidator();
        UserService userService = new UserService(userRepository, userValidator);

        System.out.println("Classes and their responsibilities:");
        System.out.println("- User: Only stores user data (name, email, id)");
        System.out.println("- UserValidator: Only validates user data");
        System.out.println("- UserRepository: Only handles data persistence");
        System.out.println("- UserService: Only coordinates business logic\n");

        // Demonstrate creating users
        System.out.println("1. Creating valid users...");
        boolean user1Created = userService.createUser("1", "Alice Johnson", "alice@email.com");
        boolean user2Created = userService.createUser("2", "Bob Smith", "bob.smith@email.com");
        
        System.out.println("\n2. Attempting to create invalid user...");
        boolean invalidUserCreated = userService.createUser("3", "X", "invalid-email-format");
        
        System.out.println("\n3. Attempting to create duplicate user...");
        boolean duplicateUserCreated = userService.createUser("1", "Duplicate Alice", "duplicate@email.com");

        System.out.println("\n4. Updating existing user...");
        boolean userUpdated = userService.updateUser("1", "Alice Updated", "alice.updated@email.com");

        System.out.println("\n5. Current users in system:");
        userService.listAllUsers();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("SRP BENEFITS DEMONSTRATED:");
        System.out.println("✓ Each class has a single, clear responsibility");
        System.out.println("✓ Changes to validation don't affect data storage");
        System.out.println("✓ Changes to storage don't affect business logic");
        System.out.println("✓ Each component can be tested independently");
        System.out.println("✓ Code is more maintainable and understandable");
        System.out.println("=".repeat(60));
    }
}