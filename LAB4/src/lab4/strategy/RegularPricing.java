package lab4.strategy;

public class RegularPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double price) {
        return price; // No discount
    }
}
