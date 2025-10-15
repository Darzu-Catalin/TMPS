package pcstore.services;

import pcstore.models.Product;
import pcstore.repositories.ProductRepository;
import pcstore.validators.ProductValidator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * ProductService (SRP: Only responsible for product business logic)
 * Demonstrates Single Responsibility Principle
 */
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    public ProductService(ProductRepository productRepository, ProductValidator productValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    public boolean addProduct(Product product) {
        if (!productValidator.validateProduct(product)) {
            System.out.println("❌ Product validation failed: " + productValidator.getValidationErrors(product));
            return false;
        }

        if (productRepository.existsById(product.getProductId())) {
            System.out.println("❌ Product with ID " + product.getProductId() + " already exists");
            return false;
        }

        productRepository.save(product);
        System.out.println("✅ Product added successfully: " + product);
        return true;
    }

    public Optional<Product> getProduct(String productId) {
        return productRepository.findById(productId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findInStock();
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<Product> searchProducts(String query) {
        return productRepository.searchByName(query);
    }

    public boolean updateProductPrice(String productId, BigDecimal newPrice) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setBasePrice(newPrice);
            productRepository.save(product);
            System.out.println("✅ Price updated for product: " + product.getName());
            return true;
        }
        System.out.println("❌ Product not found: " + productId);
        return false;
    }

    public boolean updateStock(String productId, int newStock) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setStockQuantity(newStock);
            productRepository.save(product);
            System.out.println("✅ Stock updated for product: " + product.getName() + " (New stock: " + newStock + ")");
            return true;
        }
        System.out.println("❌ Product not found: " + productId);
        return false;
    }

    public boolean isProductAvailable(String productId, int requestedQuantity) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            return productValidator.isStockSufficient(product, requestedQuantity);
        }
        return false;
    }

    public void displayInventoryReport() {
        List<Product> allProducts = productRepository.findAll();
        List<Product> inStockProducts = productRepository.findInStock();
        
        System.out.println("📊 INVENTORY REPORT");
        System.out.println("=".repeat(40));
        System.out.println("Total Products: " + allProducts.size());
        System.out.println("In Stock Products: " + inStockProducts.size());
        System.out.println("Out of Stock Products: " + (allProducts.size() - inStockProducts.size()));
        
        System.out.println("\n📦 Products by Category:");
        allProducts.stream()
                  .collect(java.util.stream.Collectors.groupingBy(Product::getCategory))
                  .forEach((category, products) -> 
                      System.out.println("  " + category + ": " + products.size() + " products"));
    }
}