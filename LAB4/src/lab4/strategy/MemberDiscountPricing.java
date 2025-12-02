package lab4.strategy;

public class MemberDiscountPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double price) {
        return price * 0.90; // 10% discount
    }
}
