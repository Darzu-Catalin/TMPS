# SOLID Principles Implementation in Java

This project demonstrates the first three SOLID principles of object-oriented design:
- **S** - Single Responsibility Principle (SRP)
- **O** - Open/Closed Principle (OCP)  
- **L** - Liskov Substitution Principle (LSP)

## Project Structure

```
LAB1/
└── src/
    └── solid/
        ├── SOLIDDemo.java          # Main demonstration class
        ├── srp/                    # Single Responsibility Principle
        │   ├── User.java
        │   ├── UserValidator.java
        │   ├── UserRepository.java
        │   └── UserService.java
        ├── ocp/                    # Open/Closed Principle
        │   ├── Shape.java
        │   ├── Rectangle.java
        │   ├── Circle.java
        │   ├── Triangle.java
        │   └── AreaCalculator.java
        └── lsp/                    # Liskov Substitution Principle
            ├── Bird.java
            ├── FlyingBird.java
            ├── Eagle.java
            ├── Sparrow.java
            ├── Penguin.java
            └── BirdManager.java
```

## How to Run

1. **Compile the project:**
   ```bash
   cd LAB1
   javac -d . src/solid/*.java src/solid/*/*.java
   ```

2. **Run the demonstration:**
   ```bash
   java solid.SOLIDDemo
   ```

## SOLID Principles Explained

### 1. Single Responsibility Principle (SRP)

> "A class should have only one reason to change."

**Implementation in `/src/solid/srp/`:**

- **`User.java`**: Responsible only for storing user data
- **`UserValidator.java`**: Responsible only for validating user data
- **`UserRepository.java`**: Responsible only for data persistence operations
- **`UserService.java`**: Responsible only for coordinating business logic

**Benefits:**
- Each class has a clear, single purpose
- Changes to validation logic don't affect data storage
- Easy to test and maintain
- High cohesion, low coupling

**Example:**
```java
// Before SRP violation:
class User {
    private String name;
    private String email;
    
    public void save() { /* database logic */ }
    public boolean validate() { /* validation logic */ }
    public void sendEmail() { /* email logic */ }
}

// After SRP compliance:
class User { /* only data */ }
class UserRepository { /* only persistence */ }
class UserValidator { /* only validation */ }
class EmailService { /* only email sending */ }
```

### 2. Open/Closed Principle (OCP)

> "Software entities should be open for extension but closed for modification."

**Implementation in `/src/solid/ocp/`:**

- **`Shape.java`**: Interface defining the contract for all shapes
- **`Rectangle.java`, `Circle.java`, `Triangle.java`**: Concrete implementations extending functionality
- **`AreaCalculator.java`**: Works with any Shape without modification

**Benefits:**
- New functionality can be added without changing existing code
- Reduces risk of introducing bugs in existing features
- Promotes code reusability and modularity

**Example:**
```java
// The Shape interface is closed for modification
interface Shape {
    double calculateArea();
    double calculatePerimeter();
}

// But open for extension - new shapes can be added
class Hexagon implements Shape {
    // New shape implementation without modifying existing code
}

// AreaCalculator works with any Shape implementation
class AreaCalculator {
    public double calculateTotalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::calculateArea).sum();
    }
}
```

### 3. Liskov Substitution Principle (LSP)

> "Objects of a superclass should be replaceable with objects of its subclasses without breaking the application."

**Implementation in `/src/solid/lsp/`:**

- **`Bird.java`**: Abstract base class with common bird behavior
- **`FlyingBird.java`**: Separate interface for flying behavior
- **`Eagle.java`, `Sparrow.java`**: Flying birds implementing both Bird and FlyingBird
- **`Penguin.java`**: Non-flying bird implementing only Bird
- **`BirdManager.java`**: Works with any Bird subclass without knowing the specific type

**Benefits:**
- Polymorphism works correctly
- Client code can work with base class references
- Substitution doesn't break functionality
- Proper inheritance hierarchies

**Example:**
```java
// LSP violation (bad design):
class Bird {
    public void fly() { /* all birds must implement */ }
}
class Penguin extends Bird {
    public void fly() { 
        throw new UnsupportedOperationException("Penguins can't fly!"); 
    }
}

// LSP compliance (good design):
abstract class Bird {
    public abstract void eat();
    public abstract void makeSound();
}
interface FlyingBird {
    void fly();
}
class Eagle extends Bird implements FlyingBird { /* can fly */ }
class Penguin extends Bird { /* cannot fly, doesn't implement FlyingBird */ }
```

## Key Takeaways

1. **SRP** leads to more maintainable and testable code by ensuring each class has a single responsibility
2. **OCP** allows systems to be easily extended with new functionality without modifying existing code
3. **LSP** ensures that inheritance hierarchies are properly designed and substitution works correctly

## Design Patterns Used

- **Strategy Pattern**: In the Shape hierarchy (OCP implementation)
- **Template Method Pattern**: In the Bird hierarchy (LSP implementation)
- **Repository Pattern**: In the User management system (SRP implementation)

## Further Extensions

This codebase demonstrates how SOLID principles work together:
- You could add new shapes (OCP) without breaking existing functionality
- You could add new bird types (LSP) that are fully substitutable
- Each responsibility is clearly separated (SRP), making the code easy to modify and extend

The principles complement each other to create a robust, maintainable, and extensible design.