package solid.lsp;

/**
 * Sparrow class - A small flying bird
 * Liskov Substitution Principle: Can be substituted for Bird without breaking functionality
 */
public class Sparrow extends Bird implements FlyingBird {
    private double maxFlightAltitude;

    public Sparrow(String name, double weight, double maxFlightAltitude) {
        super(name, weight);
        this.maxFlightAltitude = maxFlightAltitude;
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating seeds and small insects");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " chirps melodiously");
    }

    @Override
    public void fly() {
        System.out.println(name + " flies swiftly at " + maxFlightAltitude + " meters");
    }

    @Override
    public double getMaxFlightAltitude() {
        return maxFlightAltitude;
    }

    // Sparrow-specific behavior
    public void buildNest() {
        System.out.println(name + " is building a small nest");
    }
}