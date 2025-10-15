package pcstore.repositories;

import pcstore.models.Order;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

/**
 * OrderRepository class (SRP: Only responsible for order data persistence)
 * Demonstrates Single Responsibility Principle
 */
public class OrderRepository {
    private final Map<String, Order> orders;

    public OrderRepository() {
        this.orders = new HashMap<>();
    }

    // Basic CRUD operations (SRP: only data persistence)
    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public boolean deleteById(String orderId) {
        return orders.remove(orderId) != null;
    }

    public boolean existsById(String orderId) {
        return orders.containsKey(orderId);
    }

    // Query methods (SRP: only data retrieval)
    public List<Order> findByCustomerId(String customerId) {
        return orders.values().stream()
                    .filter(order -> order.getCustomerId().equals(customerId))
                    .collect(Collectors.toList());
    }

    public List<Order> findByStatus(Order.OrderStatus status) {
        return orders.values().stream()
                    .filter(order -> order.getStatus() == status)
                    .collect(Collectors.toList());
    }

    public List<Order> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orders.values().stream()
                    .filter(order -> {
                        LocalDateTime orderDate = order.getOrderDate();
                        return !orderDate.isBefore(startDate) && !orderDate.isAfter(endDate);
                    })
                    .collect(Collectors.toList());
    }

    public List<Order> findRecentOrders(int days) {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(days);
        return orders.values().stream()
                    .filter(order -> order.getOrderDate().isAfter(cutoffDate))
                    .collect(Collectors.toList());
    }

    public List<Order> findLargeOrders(double minimumTotal) {
        return orders.values().stream()
                    .filter(order -> order.getTotal().doubleValue() >= minimumTotal)
                    .collect(Collectors.toList());
    }

    public int getTotalCount() {
        return orders.size();
    }

    public long getCountByStatus(Order.OrderStatus status) {
        return orders.values().stream()
                    .filter(order -> order.getStatus() == status)
                    .count();
    }

    public long getCountByCustomer(String customerId) {
        return orders.values().stream()
                    .filter(order -> order.getCustomerId().equals(customerId))
                    .count();
    }

    public double getTotalRevenue() {
        return orders.values().stream()
                    .filter(order -> order.getStatus() != Order.OrderStatus.CANCELLED)
                    .mapToDouble(order -> order.getTotal().doubleValue())
                    .sum();
    }
}