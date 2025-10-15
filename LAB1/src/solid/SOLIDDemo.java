package solid;

import solid.srp.*;
import solid.ocp.*;
import solid.lsp.*;

import java.util.Arrays;
import java.util.List;

/**
 * Main class demonstrating all three SOLID principles
 * S - Single Responsibility Principle
 * O - Open/Closed Principle  
 * L - Liskov Substitution Principle
 */
public class SOLIDDemo {

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("SOLID PRINCIPLES DEMONSTRATION");
        System.out.println("=".repeat(80));

        // Demonstrate Single Responsibility Principle (SRP)
        demonstrateSRP();

        // Demonstrate Open/Closed Principle (OCP)
        demonstrateOCP();

        // Demonstrate Liskov Substitution Principle (LSP)
        demonstrateLSP();

        System.out.println("=".repeat(80));
        System.out.println("SOLID PRINCIPLES DEMONSTRATION COMPLETED");
        System.out.println("=".repeat(80));
    }

    /**
     * Demonstrates Single Responsibility Principle (SRP)
     * Each class has a single, well-defined responsibility
     */
    private static void demonstrateSRP() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SINGLE RESPONSIBILITY PRINCIPLE (SRP)");
        System.out.println("=".repeat(50));
        System.out.println("Each class has only one reason to change:");
        System.out.println("- User: manages user data");
        System.out.println("- UserValidator: validates user data");
        System.out.println("- UserRepository: handles data persistence");
        System.out.println("- UserService: coordinates business logic");
        System.out.println();

        // Create dependencies
        UserRepository userRepository = new UserRepository();
        UserValidator userValidator = new UserValidator();
        UserService userService = new UserService(userRepository, userValidator);

        // Demonstrate user operations
        System.out.println("Creating users...");
        userService.createUser("1", "John Doe", "john.doe@email.com");
        userService.createUser("2", "Jane Smith", "jane.smith@email.com");
        userService.createUser("3", "Invalid User", "invalid-email"); // This will fail validation

        System.out.println("\nUpdating user...");
        userService.updateUser("1", "John Updated", "john.updated@email.com");

        System.out.println("\nListing all users:");
        userService.listAllUsers();
    }

    /**
     * Demonstrates Open/Closed Principle (OCP)
     * Classes are open for extension but closed for modification
     */
    private static void demonstrateOCP() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("OPEN/CLOSED PRINCIPLE (OCP)");
        System.out.println("=".repeat(50));
        System.out.println("Classes are open for extension but closed for modification:");
        System.out.println("- Shape interface defines contract");
        System.out.println("- New shapes can be added without modifying existing code");
        System.out.println("- AreaCalculator works with any Shape implementation");
        System.out.println();

        // Create different shapes - demonstrating extension
        List<Shape> shapes = Arrays.asList(
            new Rectangle(5.0, 3.0),
            new Circle(4.0),
            new Triangle(6.0, 4.0, 5.0, 6.0, 7.0)
        );

        // AreaCalculator works with all shapes without modification
        AreaCalculator calculator = new AreaCalculator();
        calculator.printShapeDetails(shapes);

        System.out.println("\nDemo: Adding new shape types doesn't require modifying existing code!");
        System.out.println("The AreaCalculator can work with any new Shape implementation.");
    }

    /**
     * Demonstrates Liskov Substitution Principle (LSP)
     * Derived classes must be substitutable for their base classes
     */
    private static void demonstrateLSP() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LISKOV SUBSTITUTION PRINCIPLE (LSP)");
        System.out.println("=".repeat(50));
        System.out.println("Derived classes can be substituted for base classes:");
        System.out.println("- Bird is the base class");
        System.out.println("- Eagle, Sparrow, Penguin are substitutable");
        System.out.println("- FlyingBird interface separates flying behavior");
        System.out.println();

        // Create different birds - all substitutable for Bird
        List<Bird> birds = Arrays.asList(
            new Eagle("Golden Eagle", 4.5, 3000.0),
            new Sparrow("House Sparrow", 0.03, 100.0),
            new Penguin("Emperor Penguin", 30.0)
        );

        // BirdManager works with any Bird implementation
        BirdManager birdManager = new BirdManager();
        
        birdManager.printBirdInfo(birds);
        birdManager.feedAllBirds(birds);
        birdManager.makeBirdsSounds(birds);
        birdManager.makeFlyingBirdsFly(birds);
        birdManager.makeBirdsSleep(birds);

        System.out.println("Demo: All bird types can be used interchangeably!");
        System.out.println("BirdManager methods work with any Bird subclass without modification.");
    }
}