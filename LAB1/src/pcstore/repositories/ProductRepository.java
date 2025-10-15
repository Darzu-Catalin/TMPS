package pcstore.repositories;

import pcstore.models.Product;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ProductRepository class (SRP: Only responsible for product data persistence)
 * Demonstrates Single Responsibility Principle
 */
public class ProductRepository {
    private final Map<String, Product> products;

    public ProductRepository() {
        this.products = new HashMap<>();
    }

    // Basic CRUD operations (SRP: only data persistence)
    public void save(Product product) {
        products.put(product.getProductId(), product);
    }

    public Optional<Product> findById(String productId) {
        return Optional.ofNullable(products.get(productId));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public boolean deleteById(String productId) {
        return products.remove(productId) != null;
    }

    public boolean existsById(String productId) {
        return products.containsKey(productId);
    }

    // Query methods (SRP: only data retrieval)
    public List<Product> findByCategory(String category) {
        return products.values().stream()
                      .filter(product -> product.getCategory().equalsIgnoreCase(category))
                      .collect(Collectors.toList());
    }

    public List<Product> findByBrand(String brand) {
        return products.values().stream()
                      .filter(product -> product.getBrand().equalsIgnoreCase(brand))
                      .collect(Collectors.toList());
    }

    public List<Product> findInStock() {
        return products.values().stream()
                      .filter(Product::isInStock)
                      .collect(Collectors.toList());
    }

    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return products.values().stream()
                      .filter(product -> {
                          double price = product.getBasePrice().doubleValue();
                          return price >= minPrice && price <= maxPrice;
                      })
                      .collect(Collectors.toList());
    }

    public List<Product> searchByName(String nameQuery) {
        String query = nameQuery.toLowerCase();
        return products.values().stream()
                      .filter(product -> product.getName().toLowerCase().contains(query))
                      .collect(Collectors.toList());
    }

    public int getTotalCount() {
        return products.size();
    }

    public long getInStockCount() {
        return products.values().stream()
                      .filter(Product::isInStock)
                      .count();
    }

    public void updateStock(String productId, int newStock) {
        Product product = products.get(productId);
        if (product != null) {
            product.setStockQuantity(newStock);
        }
    }
}