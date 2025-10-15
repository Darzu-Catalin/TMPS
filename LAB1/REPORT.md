# LAB REPORT: SOLID Principles Implementation in Java

**Course:** Software Design Patterns and Principles  
**Laboratory:** LAB1 - SOLID Principles (S.O.L.)  
**Student:** [Your Name]  
**Date:** October 15, 2025  

---

## 1. OBJECTIVE

The objective of this laboratory work was to implement and demonstrate the first three SOLID principles of object-oriented design in Java:

- **S** - Single Responsibility Principle (SRP)
- **O** - Open/Closed Principle (OCP)
- **L** - Liskov Substitution Principle (LSP)

## 2. THEORETICAL BACKGROUND

### 2.1 SOLID Principles Overview

SOLID is an acronym for five design principles intended to make software designs more understandable, flexible, and maintainable. This laboratory focuses on the first three:

1. **Single Responsibility Principle (SRP)**: A class should have only one reason to change
2. **Open/Closed Principle (OCP)**: Software entities should be open for extension but closed for modification
3. **Liskov Substitution Principle (LSP)**: Objects of a superclass should be replaceable with objects of its subclasses without breaking the application

### 2.2 Benefits of SOLID Principles

- **Maintainability**: Easier to modify and extend code
- **Testability**: Each component can be tested independently
- **Flexibility**: Code adapts to changing requirements
- **Reusability**: Components can be reused in different contexts
- **Reduced coupling**: Components are less dependent on each other

## 3. IMPLEMENTATION

### 3.1 Project Structure

The laboratory was organized into a clear package structure:

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

### 3.2 Single Responsibility Principle (SRP) Implementation

**Problem Addressed:** Avoiding classes that have multiple reasons to change.

**Solution Implemented:**
- **User.java**: Responsible only for storing user data
- **UserValidator.java**: Handles only user data validation
- **UserRepository.java**: Manages only data persistence operations
- **UserService.java**: Coordinates only business logic

**Key Features:**
- Clear separation of concerns
- Each class has a single, well-defined responsibility
- High cohesion within classes
- Low coupling between classes

**Code Example:**
```java
// Instead of one monolithic User class handling everything:
public class User {
    // Only data management
    private String name, email, id;
    // Getters and setters only
}

public class UserValidator {
    // Only validation logic
    public boolean validateUser(User user) { ... }
}

public class UserRepository {
    // Only persistence operations
    public void save(User user) { ... }
}
```

### 3.3 Open/Closed Principle (OCP) Implementation

**Problem Addressed:** Avoiding modification of existing code when adding new functionality.

**Solution Implemented:**
- **Shape interface**: Defines contract (closed for modification)
- **Shape implementations**: Rectangle, Circle, Triangle (open for extension)
- **AreaCalculator**: Works with any Shape without modification

**Key Features:**
- New shapes can be added without changing existing code
- AreaCalculator is polymorphic and extensible
- Interface-based design promotes flexibility

**Code Example:**
```java
// Interface defines contract (closed for modification)
public interface Shape {
    double calculateArea();
    double calculatePerimeter();
}

// New shapes can be added (open for extension)
public class Rectangle implements Shape {
    public double calculateArea() { return width * height; }
}

// Calculator works with any Shape
public class AreaCalculator {
    public double calculateTotalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::calculateArea).sum();
    }
}
```

### 3.4 Liskov Substitution Principle (LSP) Implementation

**Problem Addressed:** Ensuring proper inheritance where derived classes can substitute base classes.

**Solution Implemented:**
- **Bird abstract class**: Common behavior for all birds
- **FlyingBird interface**: Separate contract for flying behavior
- **Concrete birds**: Eagle, Sparrow (flying), Penguin (non-flying)
- **BirdManager**: Works with any Bird subclass

**Key Features:**
- Proper inheritance hierarchy
- No behavioral surprises in subclasses
- Interface segregation for specific behaviors
- Full substitutability maintained

**Code Example:**
```java
// Base class with common behavior
public abstract class Bird {
    public abstract void eat();
    public abstract void makeSound();
    public void sleep() { ... } // Common implementation
}

// Separate interface for flying behavior
public interface FlyingBird {
    void fly();
}

// Proper implementations
public class Eagle extends Bird implements FlyingBird { ... }
public class Penguin extends Bird { ... } // No flying interface
```

## 4. TESTING AND DEMONSTRATION

### 4.1 Test Execution

The implementation was tested through the `SOLIDDemo` class which demonstrates:

1. **SRP Demo**: User creation, validation, and persistence with separated responsibilities
2. **OCP Demo**: Shape calculations with multiple shape types
3. **LSP Demo**: Bird management with different bird types being interchangeable

### 4.2 Compilation and Execution

**Compilation:**
```bash
javac -d bin src/solid/*.java src/solid/*/*.java
```

**Execution:**
```bash
java -cp bin solid.SOLIDDemo
```

**Results:** All demonstrations executed successfully, showing proper implementation of each principle.

## 5. ANALYSIS AND RESULTS

### 5.1 Benefits Achieved

1. **Maintainability**: Each class has a clear purpose, making modifications easier
2. **Extensibility**: New features can be added without modifying existing code
3. **Testability**: Each component can be tested independently
4. **Flexibility**: Code can adapt to changing requirements
5. **Reusability**: Components can be used in different contexts

### 5.2 Design Patterns Observed

During implementation, several design patterns emerged naturally:

- **Repository Pattern**: In the SRP implementation for data access
- **Strategy Pattern**: In the OCP implementation with Shape interface
- **Template Method Pattern**: In the LSP implementation with Bird hierarchy

### 5.3 Code Quality Metrics

- **Coupling**: Low - classes depend on abstractions, not concrete implementations
- **Cohesion**: High - each class has a single, focused responsibility
- **Complexity**: Reduced - responsibilities are distributed appropriately
- **Extensibility**: High - new functionality can be added easily

## 6. CHALLENGES AND SOLUTIONS

### 6.1 Challenge: SRP Implementation
**Problem**: Determining the right level of responsibility separation
**Solution**: Applied the "single reason to change" test to each class

### 6.2 Challenge: OCP Implementation
**Problem**: Designing interfaces that are truly closed for modification
**Solution**: Focused on essential operations that all shapes must support

### 6.3 Challenge: LSP Implementation
**Problem**: Avoiding inheritance violations (like making all birds fly)
**Solution**: Used interface segregation to separate flying behavior

## 7. CONCLUSIONS

### 7.1 Key Learnings

1. **SOLID principles work together** to create better software design
2. **Proper abstraction** is crucial for implementing these principles
3. **Interface design** is fundamental to achieving OCP and LSP
4. **Separation of concerns** (SRP) makes code more maintainable
5. **Inheritance hierarchies** require careful design to maintain LSP

### 7.2 Practical Applications

The implemented examples demonstrate real-world scenarios:
- **User management systems** benefit from SRP
- **Plugin architectures** rely on OCP
- **Framework design** depends on LSP

### 7.3 Future Extensions

The codebase is designed for easy extension:
- New user operations can be added to UserService
- New shapes can be implemented without modifying existing code
- New bird types can be added while maintaining substitutability

## 8. REFERENCES AND RESOURCES

1. Martin, R. C. (2000). Design Principles and Design Patterns
2. Martin, R. C. (2017). Clean Architecture: A Craftsman's Guide to Software Structure and Design
3. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). Design Patterns: Elements of Reusable Object-Oriented Software
4. Oracle Java Documentation: Object-Oriented Programming Concepts

---

**Laboratory Completion Status:** ✅ COMPLETED  
**All SOLID Principles Implemented:** S.O.L. ✅  
**Code Compilation:** ✅ SUCCESS  
**Demonstration Execution:** ✅ SUCCESS  

**Final Assessment:** The laboratory successfully demonstrates practical implementation of the first three SOLID principles with clear, maintainable, and extensible Java code.