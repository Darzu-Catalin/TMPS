package lab4.strategy;

public class SeasonalDiscountPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double price) {
        return price * 0.80; // 20% discount
    }
}
