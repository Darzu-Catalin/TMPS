# LAB REPORT: SOLID Principles Implementation in Java

**Course:** Software Design Patterns and Principles  
**Laboratory:** LAB1 - SOLID Principles (S.O.L.)  
**Student:** [Your Name]  
**Date:** October 15, 2025  

---

## 1. OBJECTIVE

The objective of this laboratory work was to implement and demonstrate the first three SOLID principles of object-oriented design in Java through two comprehensive projects:

1. **Academic Examples**: Basic demonstration of each principle in isolation
2. **PC Store Application**: Real-world business application implementing all principles naturally

**SOLID Principles Implemented:**
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

The laboratory consists of two complementary implementations:

#### 3.1.1 Academic Examples (SOLID Principles Demonstration)

```
LAB1/
└── src/
    └── solid/
        ├── SOLIDDemo.java              # Comprehensive demonstration
        ├── ComprehensiveSOLIDDemo.java # Alternative demo
        ├── srp/                        # Single Responsibility Principle
        │   ├── User.java
        │   ├── UserValidator.java
        │   ├── UserRepository.java
        │   └── UserService.java
        ├── ocp/                        # Open/Closed Principle
        │   ├── Shape.java
        │   ├── Rectangle.java
        │   ├── Circle.java
        │   ├── Triangle.java
        │   └── AreaCalculator.java
        └── lsp/                        # Liskov Substitution Principle
            ├── Bird.java
            ├── FlyingBird.java
            ├── Eagle.java
            ├── Sparrow.java
            ├── Penguin.java
            └── BirdManager.java
```

#### 3.1.2 PC Store Application (Real-World Implementation)

```
LAB1/
└── src/
    └── pcstore/
        ├── PCStoreApplication.java     # Main application
        ├── models/                     # Business models (SRP)
        │   ├── Product.java           # Abstract base class
        │   ├── Laptop.java            # Product extension (OCP)
        │   ├── Desktop.java           # Product extension (OCP)
        │   ├── Accessory.java         # Product extension (OCP)
        │   ├── Customer.java          # Abstract base class
        │   ├── RegularCustomer.java   # Customer type (LSP)
        │   ├── PremiumCustomer.java   # Customer type (LSP)
        │   ├── CorporateCustomer.java # Customer type (LSP)
        │   └── Order.java             # Order management
        ├── repositories/               # Data persistence (SRP)
        │   ├── ProductRepository.java
        │   ├── CustomerRepository.java
        │   └── OrderRepository.java
        ├── services/                   # Business logic (SRP)
        │   ├── ProductService.java
        │   ├── CustomerService.java
        │   ├── OrderService.java
        │   └── InventoryService.java
        ├── validators/                 # Data validation (SRP)
        │   ├── ProductValidator.java
        │   └── CustomerValidator.java
        ├── pricing/                    # Pricing strategies (OCP)
        │   ├── PricingStrategy.java
        │   ├── RegularPricingStrategy.java
        │   ├── BulkDiscountStrategy.java
        │   └── SeasonalPricingStrategy.java
        └── shipping/                   # Shipping methods (LSP)
            ├── ShippingMethod.java
            ├── StandardShipping.java
            ├── ExpressShipping.java
            └── FreeShipping.java
```

### 3.2 PC Store Application Implementation

The PC Store application demonstrates all three SOLID principles in a realistic e-commerce scenario. This implementation shows how SOLID principles naturally emerge in well-designed business applications.

#### 3.2.1 Business Domain Overview

The PC Store manages:
- **Products**: Laptops, Desktops, and Accessories with different specifications
- **Customers**: Regular, Premium, and Corporate customers with different benefits
- **Orders**: Purchase transactions with pricing, shipping, and inventory management
- **Services**: Business logic for product management, customer service, and order processing

### 3.3 Single Responsibility Principle (SRP) Implementation

#### 3.3.1 Academic Examples (Basic SRP)

**Problem Addressed:** Avoiding classes that have multiple reasons to change.

**Solution Implemented:**
- **User.java**: Responsible only for storing user data
- **UserValidator.java**: Handles only user data validation
- **UserRepository.java**: Manages only data persistence operations
- **UserService.java**: Coordinates only business logic

#### 3.3.2 PC Store SRP Implementation (Real-World Application)

**Models Package (Data Management Only):**
- **Product.java**: Abstract base class managing only product data
- **Customer.java**: Abstract base class managing only customer data  
- **Order.java**: Manages only order data and order item relationships

**Validators Package (Validation Only):**
- **ProductValidator.java**: Validates only product data integrity
- **CustomerValidator.java**: Validates only customer data and business rules

**Repositories Package (Data Persistence Only):**
- **ProductRepository.java**: Handles only product data storage and retrieval
- **CustomerRepository.java**: Manages only customer data persistence
- **OrderRepository.java**: Responsible only for order data operations

**Services Package (Business Logic Only):**
- **ProductService.java**: Coordinates only product-related business operations
- **CustomerService.java**: Manages only customer-related business logic
- **OrderService.java**: Handles only order processing and management

**Key Features:**
- Clear separation of concerns in PC Store architecture
- Each class has a single, well-defined responsibility
- High cohesion within classes, low coupling between classes
- Business logic separated from data management and validation

**Code Example (PC Store):**
```java
// Product class - only manages product data
public abstract class Product {
    protected String productId;
    protected String name;
    protected BigDecimal basePrice;
    // Only data access methods, no business logic
}

// ProductValidator - only validates product data
public class ProductValidator {
    public boolean validateProduct(Product product) { ... }
    public String getValidationErrors(Product product) { ... }
}

// ProductRepository - only handles data persistence
public class ProductRepository {
    public void save(Product product) { ... }
    public Optional<Product> findById(String id) { ... }
}

// ProductService - only coordinates business logic
public class ProductService {
    public boolean addProduct(Product product) { ... }
    public void updateInventory(String productId, int quantity) { ... }
}
```

### 3.4 Open/Closed Principle (OCP) Implementation

#### 3.4.1 Academic Examples (Basic OCP)

**Problem Addressed:** Avoiding modification of existing code when adding new functionality.

**Solution Implemented:**
- **Shape interface**: Defines contract (closed for modification)
- **Shape implementations**: Rectangle, Circle, Triangle (open for extension)
- **AreaCalculator**: Works with any Shape without modification

#### 3.4.2 PC Store OCP Implementation (Real-World Application)

**Product Hierarchy (Extensible Product System):**
- **Product.java**: Abstract base class defining the contract (closed for modification)
- **Laptop.java**: Extends Product without modifying base class
- **Desktop.java**: Extends Product without modifying base class  
- **Accessory.java**: Extends Product without modifying base class

**Pricing Strategies (Extensible Pricing System):**
- **PricingStrategy.java**: Interface defining pricing contract (closed for modification)
- **RegularPricingStrategy.java**: Standard pricing implementation
- **BulkDiscountStrategy.java**: Volume-based pricing (new extension)
- **SeasonalPricingStrategy.java**: Time-based pricing (new extension)

**Code Example (PC Store OCP):**
```java
// Product interface/abstract class - closed for modification
public abstract class Product {
    public abstract String getCategory();
    public abstract String getSpecifications();
    // Common behavior defined once
}

// Extensions - open for extension, no modification needed
public class Laptop extends Product {
    @Override
    public String getCategory() { return "Laptop"; }
    @Override
    public String getSpecifications() { return "Laptop specs..."; }
}

public class Desktop extends Product {
    @Override
    public String getCategory() { return "Desktop"; }
    @Override
    public String getSpecifications() { return "Desktop specs..."; }
}

// Pricing system - extensible without modification
public interface PricingStrategy {
    BigDecimal calculatePrice(Product product, Customer customer, int quantity);
}

// New pricing strategies can be added without changing existing code
public class SeasonalPricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calculatePrice(Product product, Customer customer, int quantity) {
        // Seasonal pricing logic
    }
}
```

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

### 3.5 Liskov Substitution Principle (LSP) Implementation

#### 3.5.1 Academic Examples (Basic LSP)

**Problem Addressed:** Ensuring proper inheritance where derived classes can substitute base classes.

**Solution Implemented:**
- **Bird abstract class**: Common behavior for all birds
- **FlyingBird interface**: Separate contract for flying behavior
- **Concrete birds**: Eagle, Sparrow (flying), Penguin (non-flying)
- **BirdManager**: Works with any Bird subclass

#### 3.5.2 PC Store LSP Implementation (Real-World Application)

**Customer Hierarchy (Substitutable Customer Types):**
- **Customer.java**: Abstract base class defining common customer behavior
- **RegularCustomer.java**: Basic customer with standard benefits (substitutable)
- **PremiumCustomer.java**: Enhanced customer with loyalty points (substitutable)
- **CorporateCustomer.java**: Business customer with credit system (substitutable)

**Key LSP Compliance Features:**
- All customer types can be used interchangeably in any method expecting Customer
- Each subclass properly implements all abstract methods without breaking contracts
- No unexpected behavior when substituting one customer type for another
- CustomerService works with any Customer subclass without modification

**Code Example (PC Store LSP):**
```java
// Base Customer class - defines contract for all customers
public abstract class Customer {
    public abstract double getDiscountPercentage();
    public abstract String getCustomerType();
    public abstract boolean isEligibleForFreeShipping(double orderTotal);
    
    // Common behavior inherited by all subclasses
    public String getFullName() { return firstName + " " + lastName; }
}

// All customer types are properly substitutable
public class RegularCustomer extends Customer {
    @Override
    public double getDiscountPercentage() { return 0.0; }
    @Override
    public boolean isEligibleForFreeShipping(double orderTotal) { 
        return orderTotal >= 100.0; 
    }
}

public class PremiumCustomer extends Customer {
    @Override
    public double getDiscountPercentage() { return 10.0; }
    @Override
    public boolean isEligibleForFreeShipping(double orderTotal) { 
        return orderTotal >= 50.0; 
    }
}

public class CorporateCustomer extends Customer {
    @Override
    public double getDiscountPercentage() { return 15.0; }
    @Override
    public boolean isEligibleForFreeShipping(double orderTotal) { 
        return true; // Always free shipping
    }
}

// CustomerService works with ANY Customer subclass (LSP compliance)
public class CustomerService {
    public void processOrder(Customer customer, Order order) {
        // This method works correctly with any Customer subclass
        double discount = customer.getDiscountPercentage();
        boolean freeShipping = customer.isEligibleForFreeShipping(order.getTotal());
        // Process order based on customer type without knowing specific type
    }
}
```

## 4. TESTING AND DEMONSTRATION

### 4.1 Test Execution

The implementation was tested through the `SOLIDDemo` class which demonstrates:

1. **SRP Demo**: User creation, validation, and persistence with separated responsibilities
2. **OCP Demo**: Shape calculations with multiple shape types
3. **LSP Demo**: Bird management with different bird types being interchangeable

### 4.1 Test Execution

The implementation was tested through multiple demonstration classes:

#### 4.1.1 Academic Examples Testing
- **SOLIDDemo.java**: Comprehensive demonstration of all three principles with detailed explanations
- **ComprehensiveSOLIDDemo.java**: Alternative demonstration focusing on package organization

#### 4.1.2 PC Store Application Testing
- **PCStoreApplication.java**: Real-world business scenario testing all principles
- **Interactive scenarios**: Customer registration, product management, order processing
- **Performance testing**: Large inventory management, bulk operations
- **Edge case testing**: Invalid data handling, constraint validation

**Test Scenarios Covered:**
1. **SRP Testing**: Separate modification of validation rules, repository logic, and business operations
2. **OCP Testing**: Addition of new product types and pricing strategies without code modification
3. **LSP Testing**: Interchangeable use of different customer types and shipping methods

### 4.2 Compilation and Execution

#### 4.2.1 Academic Examples
**Compilation:**
```bash
javac -d bin src/solid/*.java src/solid/*/*.java
```

**Execution:**
```bash
java -cp bin solid.SOLIDDemo
```

#### 4.2.2 PC Store Application
**Compilation:**
```bash
javac -d bin src/pcstore/*.java src/pcstore/*/*.java
```

**Execution:**
```bash
java -cp bin pcstore.PCStoreApplication
```

**Results:** All demonstrations executed successfully, showing proper implementation of each principle in both academic and real-world contexts.

## 5. ANALYSIS AND RESULTS

### 5.1 Benefits Achieved

#### 5.1.1 Academic Implementation Benefits
1. **Clear Understanding**: Each principle demonstrated in isolation
2. **Educational Value**: Step-by-step explanation of principle application
3. **Code Clarity**: Simple examples easy to understand and modify

#### 5.1.2 PC Store Implementation Benefits
1. **Real-World Application**: Demonstrates how principles work in business scenarios
2. **Scalability**: Easy to add new products, customers, and business rules
3. **Maintainability**: Changes to one component don't affect others
4. **Testability**: Each business component can be tested independently
5. **Flexibility**: System adapts to changing business requirements
6. **Professional Structure**: Follows industry best practices for enterprise applications

### 5.2 Design Patterns Observed

During implementation, several design patterns emerged naturally:

#### 5.2.1 Academic Examples
- **Repository Pattern**: In the SRP implementation for data access
- **Strategy Pattern**: In the OCP implementation with Shape interface
- **Template Method Pattern**: In the LSP implementation with Bird hierarchy

#### 5.2.2 PC Store Application
- **Repository Pattern**: Product, Customer, and Order repositories for data persistence
- **Strategy Pattern**: Multiple pricing strategies and shipping methods
- **Factory Pattern**: Customer creation based on customer type
- **Template Method Pattern**: Abstract Product and Customer classes
- **Command Pattern**: Order processing and inventory management

### 5.3 Code Quality Metrics

#### 5.3.1 Overall Quality Improvements
- **Coupling**: Low - classes depend on abstractions, not concrete implementations
- **Cohesion**: High - each class has a single, focused responsibility
- **Complexity**: Reduced - responsibilities are distributed appropriately
- **Extensibility**: High - new functionality can be added easily

#### 5.3.2 PC Store Specific Metrics
- **Business Logic Separation**: Clear separation between data, validation, and business rules
- **Scalability**: Easy to add new product categories and customer types
- **Maintainability**: Changes to pricing don't affect inventory management
- **Testability**: Each service can be unit tested independently
- **Performance**: Repository pattern enables efficient data access optimization

## 6. CHALLENGES AND SOLUTIONS

### 6.1 Challenge: SRP Implementation
**Problem**: Determining the right level of responsibility separation
**Solution**: Applied the "single reason to change" test to each class. In PC Store, separated data models, validation, persistence, and business logic into distinct layers.

### 6.2 Challenge: OCP Implementation  
**Problem**: Designing interfaces that are truly closed for modification
**Solution**: Focused on essential operations and used abstract classes/interfaces. PC Store's product hierarchy and pricing strategies demonstrate effective OCP implementation.

### 6.3 Challenge: LSP Implementation
**Problem**: Avoiding inheritance violations and ensuring proper substitutability
**Solution**: Used interface segregation and careful inheritance design. PC Store's customer hierarchy ensures all customer types are truly substitutable.

### 6.4 Challenge: Real-World Application Complexity
**Problem**: Applying principles in complex business scenarios without over-engineering
**Solution**: Balanced principle adherence with practical development needs, focusing on maintainability and business value.

## 7. CONCLUSIONS

### 7.1 Key Learnings

#### 7.1.1 Principle Integration
1. **SOLID principles work synergistically** to create robust software architecture
2. **Real-world applications** naturally benefit from SOLID design when properly implemented
3. **Business logic separation** improves both code quality and business agility

#### 7.1.2 Implementation Insights
1. **Proper abstraction** is crucial for implementing these principles effectively
2. **Interface design** is fundamental to achieving OCP and LSP in business applications
3. **Separation of concerns** (SRP) makes enterprise applications more maintainable
4. **Inheritance hierarchies** require careful design to maintain LSP in complex domains

### 7.2 Practical Applications

#### 7.2.1 Academic Examples
- **Educational systems** for teaching object-oriented principles
- **Simple applications** where principles can be demonstrated clearly
- **Prototype development** for validating design approaches

#### 7.2.2 PC Store Business Applications
- **E-commerce platforms** with extensible product catalogs
- **Customer management systems** with different customer tiers
- **Order processing systems** with flexible pricing and shipping
- **Inventory management** with scalable product hierarchies
- **Enterprise applications** requiring maintainable business logic

### 7.3 Future Extensions

#### 7.3.1 Academic Examples
- New user operations can be added to UserService
- New shapes can be implemented without modifying existing code
- New bird types can be added while maintaining substitutability

#### 7.3.2 PC Store Extensions
- **New Product Categories**: Gaming accessories, software, services
- **Advanced Pricing**: Dynamic pricing, loyalty rewards, bulk discounts
- **Enhanced Customer Types**: Student customers, government accounts, resellers
- **Additional Services**: Technical support, warranty management, installation services
- **Integration Capabilities**: Payment gateways, shipping providers, inventory systems

### 7.4 Business Impact

The PC Store implementation demonstrates how SOLID principles translate to:
- **Faster feature development** through extensible architectures
- **Reduced maintenance costs** through clear separation of concerns
- **Improved code quality** through better testing and modularity
- **Enhanced team productivity** through understandable code structure
- **Business agility** through adaptable system design

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

---

**Laboratory Completion Status:** ✅ COMPLETED  
**Projects Implemented:** 
- ✅ Academic SOLID Examples (Basic principle demonstration)
- ✅ PC Store Application (Real-world business implementation)
**All SOLID Principles Implemented:** S.O.L. ✅  
**Code Compilation:** ✅ SUCCESS  
**Demonstration Execution:** ✅ SUCCESS  

**Final Assessment:** The laboratory successfully demonstrates comprehensive implementation of the first three SOLID principles through both academic examples and a realistic PC Store business application. The implementation shows clear understanding of how SOLID principles improve software design, maintainability, and extensibility in real-world scenarios.