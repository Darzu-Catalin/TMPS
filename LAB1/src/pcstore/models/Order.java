package pcstore.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order class (SRP: Only manages order data)
 * Demonstrates Single Responsibility Principle
 */
public class Order {
    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private List<OrderItem> items;
    private OrderStatus status;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private BigDecimal shippingCost;
    private BigDecimal total;
    private String shippingAddress;

    public enum OrderStatus {
        PENDING, CONFIRMED, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    public Order(String orderId, String customerId, String shippingAddress) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.subtotal = BigDecimal.ZERO;
        this.discountAmount = BigDecimal.ZERO;
        this.shippingCost = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
        this.shippingAddress = shippingAddress;
    }

    // Order management methods (SRP: only order-related operations)
    public void addItem(Product product, int quantity) {
        OrderItem item = new OrderItem(product.getProductId(), product.getName(), 
                                     product.getBasePrice(), quantity);
        items.add(item);
        recalculateSubtotal();
    }

    public void removeItem(String productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
        recalculateSubtotal();
    }

    public void updateItemQuantity(String productId, int newQuantity) {
        for (OrderItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(newQuantity);
                recalculateSubtotal();
                return;
            }
        }
    }

    private void recalculateSubtotal() {
        subtotal = items.stream()
                       .map(OrderItem::getTotalPrice)
                       .reduce(BigDecimal.ZERO, BigDecimal::add);
        recalculateTotal();
    }

    public void applyDiscount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
        recalculateTotal();
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
        recalculateTotal();
    }

    private void recalculateTotal() {
        total = subtotal.subtract(discountAmount).add(shippingCost);
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    // Getters (SRP: only data access)
    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public List<OrderItem> getItems() { return new ArrayList<>(items); }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public BigDecimal getShippingCost() { return shippingCost; }
    public BigDecimal getTotal() { return total; }
    public String getShippingAddress() { return shippingAddress; }
    public int getItemCount() { return items.size(); }
    public int getTotalQuantity() { return items.stream().mapToInt(OrderItem::getQuantity).sum(); }

    @Override
    public String toString() {
        return String.format("Order{id='%s', customerId='%s', items=%d, total=$%.2f, status=%s}", 
                           orderId, customerId, items.size(), total, status);
    }

    /**
     * Inner class for Order Items (SRP: manages order item data only)
     */
    public static class OrderItem {
        private String productId;
        private String productName;
        private BigDecimal unitPrice;
        private int quantity;
        private BigDecimal totalPrice;

        public OrderItem(String productId, String productName, BigDecimal unitPrice, int quantity) {
            this.productId = productId;
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
            this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }

        // Getters
        public String getProductId() { return productId; }
        public String getProductName() { return productName; }
        public BigDecimal getUnitPrice() { return unitPrice; }
        public int getQuantity() { return quantity; }
        public BigDecimal getTotalPrice() { return totalPrice; }

        @Override
        public String toString() {
            return String.format("OrderItem{product='%s', quantity=%d, unitPrice=$%.2f, total=$%.2f}", 
                               productName, quantity, unitPrice, totalPrice);
        }
    }
}