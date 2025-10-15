package solid.lsp;

/**
 * FlyingBird interface - Separate interface for birds that can fly
 * Liskov Substitution Principle: Prevents violation by separating flying behavior
 */
public interface FlyingBird {
    void fly();
    double getMaxFlightAltitude();
}