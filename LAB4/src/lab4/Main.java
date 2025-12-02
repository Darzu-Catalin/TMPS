package lab4;

import lab4.chain.HardwareSpecialist;
import lab4.chain.Level1Support;
import lab4.chain.Level2Support;
import lab4.chain.SupportHandler;
import lab4.observer.Customer;
import lab4.observer.Product;
import lab4.strategy.MemberDiscountPricing;
import lab4.strategy.PricingStrategy;
import lab4.strategy.RegularPricing;
import lab4.strategy.SeasonalDiscountPricing;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== LAB4: Behavioral Design Patterns Demo ===\n");

        // --- 1. Strategy Pattern Demo ---
        System.out.println("--- 1. Strategy Pattern: Dynamic Pricing ---");
        double basePrice = 1000.0;
        
        PricingStrategy regular = new RegularPricing();
        PricingStrategy member = new MemberDiscountPricing();
        PricingStrategy seasonal = new SeasonalDiscountPricing();

        System.out.println("Base Price: $" + basePrice);
        System.out.println("Regular Price: $" + regular.calculatePrice(basePrice));
        System.out.println("Member Price: $" + member.calculatePrice(basePrice));
        System.out.println("Seasonal Price: $" + seasonal.calculatePrice(basePrice));
        System.out.println();

        // --- 2. Observer Pattern Demo ---
        System.out.println("--- 2. Observer Pattern: Stock Notifications ---");
        Product gpu = new Product("RTX 5090");
        Customer alice = new Customer("Alice");
        Customer bob = new Customer("Bob");

        gpu.addObserver(alice);
        gpu.addObserver(bob);

        System.out.println("Restocking RTX 5090...");
        gpu.setInStock(true); // Should notify Alice and Bob
        System.out.println();

        // --- 3. Chain of Responsibility Pattern Demo ---
        System.out.println("--- 3. Chain of Responsibility: Support System ---");
        
        // Setup Chain
        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler hardware = new HardwareSpecialist();

        level1.setNextHandler(level2);
        level2.setNextHandler(hardware);

        // Test Requests
        System.out.println("Ticket #1: 'I forgot my password' (Severity 1)");
        level1.handleRequest("I forgot my password", 1);
        System.out.println();

        System.out.println("Ticket #2: 'System crashes when opening app' (Severity 2)");
        level1.handleRequest("System crashes when opening app", 2);
        System.out.println();

        System.out.println("Ticket #3: 'Smoke coming from power supply' (Severity 3)");
        level1.handleRequest("Smoke coming from power supply", 3);
        System.out.println();
        
        System.out.println("Ticket #4: 'Aliens took my PC' (Severity 4)");
        level1.handleRequest("Aliens took my PC", 4);
    }
}
