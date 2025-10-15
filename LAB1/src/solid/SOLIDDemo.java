package solid;

import solid.srp.*;
import solid.ocp.*;
import solid.lsp.*;

import java.util.Arrays;
import java.util.List;

/**
 * Enhanced SOLID Principles Demonstration
 * 
 * This class demonstrates all three SOLID principles with detailed functionality:
 * S - Single Responsibility Principle (SRP)
 * O - Open/Closed Principle (OCP)  
 * L - Liskov Substitution Principle (LSP)
 * 
 * Each principle is demonstrated with multiple scenarios and clear explanations.
 */
public class SOLIDDemo {

    public static void main(String[] args) {
        printHeader();
        
        // Demonstrate all three SOLID principles with full functionality
        demonstrateSRP();
        printSeparator();
        
        demonstrateOCP();
        printSeparator();
        
        demonstrateLSP();
        printSeparator();
        
        printSummary();
    }

    private static void printHeader() {
        System.out.println("🎯".repeat(40));
        System.out.println("           SOLID PRINCIPLES COMPREHENSIVE DEMONSTRATION");
        System.out.println("🎯".repeat(40));
        System.out.println("📚 Demonstrating the first 3 SOLID principles:");
        System.out.println("   S - Single Responsibility Principle");
        System.out.println("   O - Open/Closed Principle");
        System.out.println("   L - Liskov Substitution Principle");
        System.out.println("🎯".repeat(40));
        System.out.println();
    }

    private static void printSeparator() {
        System.out.println("\n" + "─".repeat(80) + "\n");
    }

    private static void printSummary() {
        System.out.println("🎉 DEMONSTRATION COMPLETE!");
        System.out.println("=" .repeat(50));
        System.out.println("✅ Single Responsibility Principle - Each class has one job");
        System.out.println("✅ Open/Closed Principle - Open for extension, closed for modification");
        System.out.println("✅ Liskov Substitution Principle - Subclasses are substitutable");
        System.out.println("=" .repeat(50));
        System.out.println("💡 These principles work together to create maintainable,");
        System.out.println("   flexible, and robust object-oriented software!");
    }

    /**
     * Enhanced Single Responsibility Principle (SRP) Demonstration
     * Each class has a single, well-defined responsibility
     */
    private static void demonstrateSRP() {
        System.out.println("🔹 SINGLE RESPONSIBILITY PRINCIPLE (SRP)");
        System.out.println("=" .repeat(60));
        System.out.println("🎯 Principle: Each class should have only ONE reason to change");
        System.out.println();
        
        System.out.println("📋 Classes and their SINGLE responsibilities:");
        System.out.println("   🗂️  User: Stores user data only");
        System.out.println("   ✅ UserValidator: Validates user data only");
        System.out.println("   💾 UserRepository: Handles data persistence only");
        System.out.println("   🎛️  UserService: Coordinates business logic only");
        System.out.println();

        // Create dependencies with separated responsibilities
        UserRepository userRepository = new UserRepository();
        UserValidator userValidator = new UserValidator();
        UserService userService = new UserService(userRepository, userValidator);

        System.out.println("🧪 TESTING SRP FUNCTIONALITY:");
        System.out.println("─".repeat(40));
        
        // Test 1: Valid user creation
        System.out.println("📝 Test 1: Creating valid users");
        userService.createUser("1", "Alice Johnson", "alice@example.com");
        userService.createUser("2", "Bob Smith", "bob@example.com");
        
        // Test 2: Invalid user creation (validation fails)
        System.out.println("\n📝 Test 2: Creating invalid user (should fail)");
        userService.createUser("3", "X", "invalid-email-format");
        
        // Test 3: Duplicate user creation
        System.out.println("\n📝 Test 3: Creating duplicate user (should fail)");
        userService.createUser("1", "Another Alice", "another@example.com");
        
        // Test 4: User update
        System.out.println("\n📝 Test 4: Updating existing user");
        userService.updateUser("1", "Alice Updated", "alice.updated@example.com");
        
        // Test 5: List all users
        System.out.println("\n📝 Test 5: Listing all users");
        userService.listAllUsers();
        
        System.out.println("\n💡 SRP BENEFITS DEMONSTRATED:");
        System.out.println("   ✅ Each class can be tested independently");
        System.out.println("   ✅ Changes to validation don't affect data storage");
        System.out.println("   ✅ Business logic is separated from data operations");
        System.out.println("   ✅ Code is more maintainable and readable");
    }

    /**
     * Enhanced Open/Closed Principle (OCP) Demonstration
     * Classes are open for extension but closed for modification
     */
    private static void demonstrateOCP() {
        System.out.println("🔹 OPEN/CLOSED PRINCIPLE (OCP)");
        System.out.println("=" .repeat(60));
        System.out.println("🎯 Principle: Open for EXTENSION, closed for MODIFICATION");
        System.out.println();
        
        System.out.println("📋 OCP Implementation:");
        System.out.println("   🔒 Shape interface: CLOSED for modification (defines contract)");
        System.out.println("   🔓 Shape implementations: OPEN for extension (new shapes)");
        System.out.println("   🧮 AreaCalculator: Works with ANY shape without modification");
        System.out.println();

        System.out.println("🧪 TESTING OCP FUNCTIONALITY:");
        System.out.println("─".repeat(40));
        
        // Test 1: Create various shapes (demonstrating extension)
        System.out.println("📝 Test 1: Creating different shapes (extensions)");
        List<Shape> shapes = Arrays.asList(
            new Rectangle(5.0, 3.0),
            new Circle(4.0),
            new Triangle(6.0, 4.0, 5.0, 6.0, 7.0)
        );
        
        for (Shape shape : shapes) {
            System.out.println("   ✅ Created: " + shape.toString());
        }

        // Test 2: AreaCalculator works with all shapes without modification
        System.out.println("\n📝 Test 2: AreaCalculator processing all shapes");
        AreaCalculator calculator = new AreaCalculator();
        calculator.printShapeDetails(shapes);

        // Test 3: Demonstrate extensibility
        System.out.println("\n📝 Test 3: Demonstrating extensibility");
        System.out.println("   🔧 Adding new shapes doesn't require modifying:");
        System.out.println("      - Shape interface");
        System.out.println("      - AreaCalculator class");
        System.out.println("      - Existing shape implementations");
        
        // Test 4: Individual shape operations
        System.out.println("\n📝 Test 4: Individual shape calculations");
        for (Shape shape : shapes) {
            System.out.printf("   %s: Area=%.2f, Perimeter=%.2f%n", 
                shape.getShapeType(), shape.calculateArea(), shape.calculatePerimeter());
        }
        
        System.out.println("\n💡 OCP BENEFITS DEMONSTRATED:");
        System.out.println("   ✅ New functionality added without modifying existing code");
        System.out.println("   ✅ AreaCalculator is polymorphic and extensible");
        System.out.println("   ✅ Reduced risk of introducing bugs in existing features");
        System.out.println("   ✅ Code follows polymorphic design patterns");
    }

    /**
     * Enhanced Liskov Substitution Principle (LSP) Demonstration
     * Derived classes must be substitutable for their base classes
     */
    private static void demonstrateLSP() {
        System.out.println("🔹 LISKOV SUBSTITUTION PRINCIPLE (LSP)");
        System.out.println("=" .repeat(60));
        System.out.println("🎯 Principle: Subclasses must be substitutable for their base classes");
        System.out.println();
        
        System.out.println("📋 LSP Implementation:");
        System.out.println("   🐦 Bird: Abstract base class with common behavior");
        System.out.println("   ✈️  FlyingBird: Interface for birds that can fly");
        System.out.println("   🦅 Eagle & Sparrow: Flying birds (implement both)");
        System.out.println("   🐧 Penguin: Non-flying bird (implements only Bird)");
        System.out.println("   🎛️  BirdManager: Works with ANY Bird subclass");
        System.out.println();

        System.out.println("🧪 TESTING LSP FUNCTIONALITY:");
        System.out.println("─".repeat(40));
        
        // Test 1: Create different bird types (all substitutable)
        System.out.println("📝 Test 1: Creating different bird types");
        List<Bird> birds = Arrays.asList(
            new Eagle("Golden Eagle", 4.5, 3000.0),
            new Sparrow("House Sparrow", 0.03, 100.0),
            new Penguin("Emperor Penguin", 30.0)
        );
        
        for (Bird bird : birds) {
            System.out.println("   ✅ Created: " + bird.toString());
        }

        BirdManager birdManager = new BirdManager();
        
        // Test 2: All birds can perform common operations (LSP compliance)
        System.out.println("\n📝 Test 2: Common bird operations (all birds)");
        birdManager.printBirdInfo(birds);
        
        System.out.println("📝 Test 3: Feeding all birds (polymorphic behavior)");
        birdManager.feedAllBirds(birds);
        
        System.out.println("📝 Test 4: Birds making sounds (different implementations)");
        birdManager.makeBirdsSounds(birds);
        
        // Test 5: Flying operations (interface segregation)
        System.out.println("📝 Test 5: Flying operations (interface segregation)");
        birdManager.makeFlyingBirdsFly(birds);
        
        // Test 6: Sleep operations (common inherited behavior)
        System.out.println("📝 Test 6: All birds sleeping (inherited behavior)");
        birdManager.makeBirdsSleep(birds);
        
        // Test 7: Demonstrate substitutability
        System.out.println("📝 Test 7: Demonstrating substitutability");
        System.out.println("   🔄 Any Bird object can be used in place of another:");
        for (Bird bird : birds) {
            demonstrateBirdSubstitution(bird);
        }
        
        System.out.println("\n💡 LSP BENEFITS DEMONSTRATED:");
        System.out.println("   ✅ All bird types work interchangeably in BirdManager");
        System.out.println("   ✅ No unexpected behavior when substituting subclasses");
        System.out.println("   ✅ Proper inheritance hierarchy with interface segregation");
        System.out.println("   ✅ Polymorphism works correctly across all implementations");
    }
    
    /**
     * Helper method to demonstrate bird substitutability
     */
    private static void demonstrateBirdSubstitution(Bird bird) {
        // This method works with ANY Bird subclass - demonstrating LSP
        System.out.printf("      🔄 Processing %s: ", bird.getName());
        bird.makeSound(); // Polymorphic call works for all Bird subclasses
    }
}