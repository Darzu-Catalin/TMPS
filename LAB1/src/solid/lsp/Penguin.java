package solid.lsp;

/**
 * Penguin class - A non-flying bird
 * Liskov Substitution Principle: Can be substituted for Bird without breaking functionality
 * Note: Doesn't implement FlyingBird interface as penguins cannot fly
 */
public class Penguin extends Bird {

    public Penguin(String name, double weight) {
        super(name, weight);
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating fish and krill");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " makes trumpet-like calls");
    }

    // Penguin-specific behavior
    public void swim() {
        System.out.println(name + " is swimming gracefully underwater");
    }

    public void slide() {
        System.out.println(name + " is sliding on its belly across the ice");
    }
}