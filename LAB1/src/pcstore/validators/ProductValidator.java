package pcstore.validators;

import pcstore.models.Product;
import java.math.BigDecimal;

/**
 * ProductValidator class (SRP: Only responsible for product validation)
 * Demonstrates Single Responsibility Principle
 */
public class ProductValidator {

    public boolean validateProduct(Product product) {
        return validateId(product.getProductId()) &&
               validateName(product.getName()) &&
               validateBrand(product.getBrand()) &&
               validatePrice(product.getBasePrice()) &&
               validateStock(product.getStockQuantity()) &&
               validateDescription(product.getDescription());
    }

    private boolean validateId(String id) {
        return id != null && !id.trim().isEmpty() && id.length() >= 3;
    }

    private boolean validateName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }

    private boolean validateBrand(String brand) {
        return brand != null && !brand.trim().isEmpty();
    }

    private boolean validatePrice(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean validateStock(int stockQuantity) {
        return stockQuantity >= 0;
    }

    private boolean validateDescription(String description) {
        return description != null && !description.trim().isEmpty();
    }

    public String getValidationErrors(Product product) {
        StringBuilder errors = new StringBuilder();
        
        if (!validateId(product.getProductId())) {
            errors.append("Invalid product ID: must be at least 3 characters. ");
        }
        if (!validateName(product.getName())) {
            errors.append("Invalid product name: must be at least 2 characters. ");
        }
        if (!validateBrand(product.getBrand())) {
            errors.append("Invalid brand: cannot be empty. ");
        }
        if (!validatePrice(product.getBasePrice())) {
            errors.append("Invalid price: must be greater than 0. ");
        }
        if (!validateStock(product.getStockQuantity())) {
            errors.append("Invalid stock quantity: cannot be negative. ");
        }
        if (!validateDescription(product.getDescription())) {
            errors.append("Invalid description: cannot be empty. ");
        }
        
        return errors.toString();
    }

    public boolean isStockSufficient(Product product, int requestedQuantity) {
        return product.getStockQuantity() >= requestedQuantity;
    }

    public boolean isPriceReasonable(Product product, BigDecimal maxPrice) {
        return product.getBasePrice().compareTo(maxPrice) <= 0;
    }
}