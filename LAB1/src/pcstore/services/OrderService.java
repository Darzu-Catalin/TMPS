package pcstore.services;

import pcstore.models.*;
import pcstore.repositories.OrderRepository;
import pcstore.pricing.*;
import pcstore.shipping.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

/**
 * OrderService (SRP: Only responsible for order business logic)
 * Demonstrates Single Responsibility Principle
 */
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final List<PricingStrategy> availablePricingStrategies;
    private final List<ShippingCalculator> availableShippingOptions;
    private int orderCounter = 1;

    public OrderService(OrderRepository orderRepository, ProductService productService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
        
        // OCP: Extensible pricing strategies
        this.availablePricingStrategies = Arrays.asList(
            new RegularPricingStrategy(),
            new BulkPricingStrategy(),
            new PromotionalPricingStrategy(15.0, "Holiday Special")
        );
        
        // OCP & LSP: Extensible and substitutable shipping options
        this.availableShippingOptions = Arrays.asList(
            new StandardShipping(),
            new ExpressShipping(),
            new OvernightShipping()
        );
    }

    public boolean createOrder(String customerId, String shippingAddress) {
        Optional<Customer> customerOpt = customerService.getCustomer(customerId);
        if (customerOpt.isEmpty()) {
            System.out.println("❌ Customer not found: " + customerId);
            return false;
        }

        String orderId = "ORD" + String.format("%03d", orderCounter++);
        Order order = new Order(orderId, customerId, shippingAddress);
        orderRepository.save(order);
        
        System.out.println("✅ Order created: " + orderId + " for customer " + customerId);
        return true;
    }

    public boolean addItemToOrder(String orderId, String productId, int quantity) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        Optional<Product> productOpt = productService.getProduct(productId);
        
        if (orderOpt.isEmpty()) {
            System.out.println("❌ Order not found: " + orderId);
            return false;
        }
        
        if (productOpt.isEmpty()) {
            System.out.println("❌ Product not found: " + productId);
            return false;
        }
        
        Order order = orderOpt.get();
        Product product = productOpt.get();
        
        if (!productService.isProductAvailable(productId, quantity)) {
            System.out.println("❌ Insufficient stock for product: " + product.getName() + " (Requested: " + quantity + ", Available: " + product.getStockQuantity() + ")");
            return false;
        }
        
        order.addItem(product, quantity);
        orderRepository.save(order);
        
        // Reduce stock
        productService.updateStock(productId, product.getStockQuantity() - quantity);
        
        System.out.println("✅ Added " + quantity + "x " + product.getName() + " to order " + orderId);
        return true;
    }

    public boolean processOrder(String orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            System.out.println("❌ Order not found: " + orderId);
            return false;
        }
        
        Order order = orderOpt.get();
        Optional<Customer> customerOpt = customerService.getCustomer(order.getCustomerId());
        
        if (customerOpt.isEmpty()) {
            System.out.println("❌ Customer not found for order: " + orderId);
            return false;
        }
        
        Customer customer = customerOpt.get();
        
        // Apply best pricing strategy (OCP: using different strategies)
        PricingStrategy bestStrategy = findBestPricingStrategy(order, customer);
        applyPricing(order, customer, bestStrategy);
        
        // Calculate shipping (LSP: any shipping calculator can be used)
        ShippingCalculator shippingCalculator = findBestShippingOption(customer);
        applyShipping(order, customer, shippingCalculator);
        
        order.updateStatus(Order.OrderStatus.CONFIRMED);
        orderRepository.save(order);
        
        System.out.println("✅ Order processed successfully: " + orderId);
        displayOrderSummary(order, customer, bestStrategy, shippingCalculator);
        
        return true;
    }

    private PricingStrategy findBestPricingStrategy(Order order, Customer customer) {
        // Find the strategy that gives the best price
        PricingStrategy bestStrategy = availablePricingStrategies.get(0);
        BigDecimal bestTotal = BigDecimal.valueOf(Double.MAX_VALUE);
        
        for (PricingStrategy strategy : availablePricingStrategies) {
            BigDecimal total = calculateOrderTotal(order, customer, strategy);
            if (total.compareTo(bestTotal) < 0) {
                bestTotal = total;
                bestStrategy = strategy;
            }
        }
        
        return bestStrategy;
    }

    private BigDecimal calculateOrderTotal(Order order, Customer customer, PricingStrategy strategy) {
        return order.getItems().stream()
                   .map(item -> {
                       Optional<Product> productOpt = productService.getProduct(item.getProductId());
                       if (productOpt.isPresent()) {
                           return strategy.calculatePrice(productOpt.get(), customer, item.getQuantity());
                       }
                       return BigDecimal.ZERO;
                   })
                   .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void applyPricing(Order order, Customer customer, PricingStrategy strategy) {
        BigDecimal originalTotal = order.getSubtotal();
        BigDecimal newTotal = calculateOrderTotal(order, customer, strategy);
        BigDecimal discount = originalTotal.subtract(newTotal);
        
        order.applyDiscount(discount);
    }

    private ShippingCalculator findBestShippingOption(Customer customer) {
        // Find the most premium shipping option available to the customer
        for (ShippingCalculator shipping : availableShippingOptions) {
            if (shipping.isAvailableForCustomer(customer)) {
                return shipping;
            }
        }
        return availableShippingOptions.get(0); // Default to standard shipping
    }

    private void applyShipping(Order order, Customer customer, ShippingCalculator shippingCalculator) {
        BigDecimal shippingCost = shippingCalculator.calculateShippingCost(
            customer, order.getSubtotal().doubleValue(), 2.5); // Assume 2.5kg average weight
        order.setShippingCost(shippingCost);
    }

    private void displayOrderSummary(Order order, Customer customer, PricingStrategy pricing, ShippingCalculator shipping) {
        System.out.println("\n📋 ORDER SUMMARY");
        System.out.println("=" .repeat(40));
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + customer.getFullName() + " (" + customer.getCustomerType() + ")");
        System.out.println("Pricing Strategy: " + pricing.getStrategyName());
        System.out.println("Shipping Method: " + shipping.getShippingMethod());
        System.out.println("Items:");
        
        for (Order.OrderItem item : order.getItems()) {
            System.out.printf("  - %s x%d @ $%.2f each = $%.2f%n", 
                item.getProductName(), item.getQuantity(), item.getUnitPrice(), item.getTotalPrice());
        }
        
        System.out.printf("Subtotal: $%.2f%n", order.getSubtotal());
        System.out.printf("Discount: -$%.2f%n", order.getDiscountAmount());
        System.out.printf("Shipping: $%.2f%n", order.getShippingCost());
        System.out.printf("TOTAL: $%.2f%n", order.getTotal());
        System.out.println("=" .repeat(40));
    }

    public Optional<Order> getOrder(String orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void displayOrderReport() {
        List<Order> allOrders = orderRepository.findAll();
        
        System.out.println("📦 ORDER REPORT");
        System.out.println("=".repeat(40));
        System.out.println("Total Orders: " + allOrders.size());
        System.out.printf("Total Revenue: $%.2f%n", orderRepository.getTotalRevenue());
        
        System.out.println("\n📊 Orders by Status:");
        for (Order.OrderStatus status : Order.OrderStatus.values()) {
            long count = orderRepository.getCountByStatus(status);
            if (count > 0) {
                System.out.println("  " + status + ": " + count + " orders");
            }
        }
        
        if (!allOrders.isEmpty()) {
            System.out.println("\n📋 Recent Orders:");
            allOrders.stream()
                    .limit(3)
                    .forEach(order -> System.out.println("  " + order));
        }
    }

    // Initialize with some processed orders for demonstration
    public void initializeSampleOrders() {
        // This method would be called after setting up products and customers
        if (orderRepository.getTotalCount() > 0) {
            // Process the first few orders for demonstration
            processOrder("ORD001");
            processOrder("ORD002");
            processOrder("ORD003");
        }
    }
}