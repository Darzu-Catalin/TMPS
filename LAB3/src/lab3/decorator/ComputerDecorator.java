package lab3.decorator;

public abstract class ComputerDecorator implements Computer {
    protected Computer computer;

    public ComputerDecorator(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String getDescription() {
        return computer.getDescription();
    }

    @Override
    public double getCost() {
        return computer.getCost();
    }
}
