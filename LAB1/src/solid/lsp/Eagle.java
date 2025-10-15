package solid.lsp;

/**
 * Eagle class - A flying bird
 * Liskov Substitution Principle: Can be substituted for Bird without breaking functionality
 */
public class Eagle extends Bird implements FlyingBird {
    private double maxFlightAltitude;

    public Eagle(String name, double weight, double maxFlightAltitude) {
        super(name, weight);
        this.maxFlightAltitude = maxFlightAltitude;
    }

    @Override
    public void eat() {
        System.out.println(name + " is hunting and eating prey");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " screeches loudly");
    }

    @Override
    public void fly() {
        System.out.println(name + " soars high in the sky at " + maxFlightAltitude + " meters");
    }

    @Override
    public double getMaxFlightAltitude() {
        return maxFlightAltitude;
    }

    // Eagle-specific behavior
    public void hunt() {
        System.out.println(name + " is hunting with sharp talons");
    }
}