package solid.lsp;

/**
 * Bird abstract class - Base class for all birds
 * Liskov Substitution Principle: Defines common behavior for all birds
 */
public abstract class Bird {
    protected String name;
    protected double weight;

    public Bird(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public abstract void eat();
    public abstract void makeSound();

    // Common behavior for all birds
    public void sleep() {
        System.out.println(name + " is sleeping");
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s(name='%s', weight=%.2f)", getClass().getSimpleName(), name, weight);
    }
}