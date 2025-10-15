package pcstore;

import pcstore.models.*;
import pcstore.services.*;
import pcstore.repositories.*;
import pcstore.validators.*;
import pcstore.pricing.*;
import pcstore.shipping.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * PC Store Application - Demonstrates all SOLID principles working together
 * 
 * This application showcases:
 * - SRP: Each class has a single responsibility
 * - OCP: Extensible product types, pricing strategies, and shipping methods
 * - LSP: Customer types are interchangeable, shipping calculators are substitutable
 */
public class PCStoreApplication {

    public static void main(String[] args) {
        System.out.println("🖥️".repeat(60));
        System.out.println("           WELCOME TO TECH WORLD PC STORE");
        System.out.println("         Demonstrating SOLID Principles in Action");
        System.out.println("🖥️".repeat(60));
        
        // Initialize the store
        PCStore store = new PCStore();
        
        // Run comprehensive demonstration
        store.runDemo();
        
        System.out.println("\n🎉 PC Store demonstration completed!");
        System.out.println("All SOLID principles successfully demonstrated in a real-world scenario!");
    }
}

/**
 * Main PC Store class that coordinates all components
 */
class PCStore {
    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public PCStore() {
        // Initialize repositories (SRP: data persistence only)
        ProductRepository productRepository = new ProductRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();
        
        // Initialize validators (SRP: validation only)
        ProductValidator productValidator = new ProductValidator();
        CustomerValidator customerValidator = new CustomerValidator();
        
        // Initialize services (SRP: business logic coordination only)
        this.productService = new ProductService(productRepository, productValidator);
        this.customerService = new CustomerService(customerRepository, customerValidator);
        this.orderService = new OrderService(orderRepository, productService, customerService);
    }

    public void runDemo() {
        System.out.println("\n🔧 SETTING UP STORE INVENTORY");
        setupInventory();
        
        System.out.println("\n👥 REGISTERING CUSTOMERS");
        setupCustomers();
        
        System.out.println("\n🛒 PROCESSING ORDERS");
        demonstrateOrders();
        
        System.out.println("\n💰 TESTING PRICING STRATEGIES (OCP)");
        demonstratePricingStrategies();
        
        System.out.println("\n🚚 TESTING SHIPPING OPTIONS (OCP & LSP)");
        demonstrateShippingStrategies();
        
        System.out.println("\n📊 GENERATING REPORTS");
        generateReports();
    }

    private void setupInventory() {
        // OCP: Different product types extending base Product class
        System.out.println("📦 Adding laptops...");
        productService.addProduct(new Laptop("LP001", "MacBook Pro", "Apple", 
            new BigDecimal("2499.00"), 10, "High-performance laptop for professionals",
            "Apple M2 Pro", 16, 512, "14-inch", "macOS"));
            
        productService.addProduct(new Laptop("LP002", "ThinkPad X1", "Lenovo", 
            new BigDecimal("1899.00"), 15, "Business laptop with excellent keyboard",
            "Intel i7-12th Gen", 32, 1000, "14-inch", "Windows 11"));
            
        System.out.println("🖥️ Adding desktops...");
        productService.addProduct(new Desktop("DT001", "Gaming Beast", "ASUS", 
            new BigDecimal("1599.00"), 8, "High-end gaming desktop",
            "AMD Ryzen 9", 32, 1000, "RTX 4070", "ASUS ROG", true));
            
        productService.addProduct(new Desktop("DT002", "Office Pro", "Dell", 
            new BigDecimal("899.00"), 12, "Reliable desktop for office work",
            "Intel i5-12th Gen", 16, 500, "Intel UHD", "Dell OptiPlex", true));
            
        System.out.println("🎧 Adding accessories...");
        productService.addProduct(new Accessory("AC001", "Wireless Mouse", "Logitech", 
            new BigDecimal("79.99"), 50, "Ergonomic wireless mouse",
            "Mouse", "Universal", "Black", true));
            
        productService.addProduct(new Accessory("AC002", "Mechanical Keyboard", "Corsair", 
            new BigDecimal("159.99"), 25, "RGB mechanical gaming keyboard",
            "Keyboard", "Universal", "Black", false));
    }

    private void setupCustomers() {
        // LSP: Different customer types all substitutable for base Customer class
        System.out.println("👤 Registering regular customers...");
        customerService.registerCustomer(new RegularCustomer("REG001", "John", "Doe", 
            "john.doe@email.com", "+1234567890", "123 Main St, Anytown, USA"));
            
        System.out.println("⭐ Registering premium customers...");
        customerService.registerCustomer(new PremiumCustomer("PREM001", "Jane", "Smith", 
            "jane.smith@email.com", "+1234567891", "456 Oak Ave, Somewhere, USA", 500));
            
        System.out.println("🏢 Registering corporate customers...");
        customerService.registerCustomer(new CorporateCustomer("CORP001", "Alice", "Johnson", 
            "alice@techcorp.com", "+1234567892", "789 Business Blvd, Corporate City, USA",
            "TechCorp Inc.", "TAX123456", 10000.0));
    }

    private void demonstrateOrders() {
        System.out.println("🛍️ Creating sample orders...");
        
        // Regular customer order
        orderService.createOrder("REG001", "123 Main St, Anytown, USA");
        orderService.addItemToOrder("ORD001", "LP001", 1); // MacBook Pro
        orderService.addItemToOrder("ORD001", "AC001", 2); // Wireless Mouse x2
        
        // Premium customer order
        orderService.createOrder("PREM001", "456 Oak Ave, Somewhere, USA");
        orderService.addItemToOrder("ORD002", "DT001", 1); // Gaming Beast
        orderService.addItemToOrder("ORD002", "AC002", 1); // Mechanical Keyboard
        
        // Corporate customer bulk order
        orderService.createOrder("CORP001", "789 Business Blvd, Corporate City, USA");
        orderService.addItemToOrder("ORD003", "LP002", 5); // ThinkPad X1 x5 (bulk)
        orderService.addItemToOrder("ORD003", "DT002", 3); // Office Pro x3
        orderService.addItemToOrder("ORD003", "AC001", 10); // Wireless Mouse x10
        
        System.out.println("\n💳 Processing orders...");
        orderService.processOrder("ORD001");
        orderService.processOrder("ORD002");
        orderService.processOrder("ORD003");
    }

    private void demonstratePricingStrategies() {
        // OCP: Different pricing strategies without modifying existing code
        System.out.println("💲 Testing different pricing strategies...");
        
        List<PricingStrategy> strategies = Arrays.asList(
            new RegularPricingStrategy(),
            new BulkPricingStrategy(),
            new PromotionalPricingStrategy(20.0, "Black Friday Sale")
        );
        
        // Get a sample product and customer
        Product laptop = productService.getProduct("LP001").orElse(null);
        Customer customer = customerService.getCustomer("PREM001").orElse(null);
        
        if (laptop != null && customer != null) {
            System.out.println("\n🧪 Pricing " + laptop.getName() + " for " + customer.getCustomerType() + ":");
            
            for (PricingStrategy strategy : strategies) {
                BigDecimal price = strategy.calculatePrice(laptop, customer, 3);
                System.out.printf("  %s: $%.2f%n", strategy.getStrategyName(), price);
            }
        }
    }

    private void demonstrateShippingStrategies() {
        // OCP & LSP: Different shipping calculators, all substitutable
        System.out.println("📦 Testing shipping options for different customers...");
        
        List<ShippingCalculator> shippingOptions = Arrays.asList(
            new StandardShipping(),
            new ExpressShipping(),
            new OvernightShipping()
        );
        
        List<Customer> customers = Arrays.asList(
            customerService.getCustomer("REG001").orElse(null),
            customerService.getCustomer("PREM001").orElse(null),
            customerService.getCustomer("CORP001").orElse(null)
        );
        
        for (Customer customer : customers) {
            if (customer != null) {
                System.out.println("\n📋 Shipping options for " + customer.getCustomerType() + ":");
                
                for (ShippingCalculator shipping : shippingOptions) {
                    if (shipping.isAvailableForCustomer(customer)) {
                        BigDecimal cost = shipping.calculateShippingCost(customer, 500.0, 2.5);
                        System.out.printf("  ✅ %s: $%.2f (%d days)%n", 
                            shipping.getShippingMethod(), cost, shipping.getEstimatedDeliveryDays());
                    } else {
                        System.out.printf("  ❌ %s: Not available%n", shipping.getShippingMethod());
                    }
                }
            }
        }
    }

    private void generateReports() {
        System.out.println("📈 Store performance reports...");
        productService.displayInventoryReport();
        System.out.println();
        customerService.displayCustomerReport();
        System.out.println();
        orderService.displayOrderReport();
    }
}