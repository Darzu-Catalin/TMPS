package lab3.decorator;

public class ExtraRAMDecorator extends ComputerDecorator {
    public ExtraRAMDecorator(Computer computer) {
        super(computer);
    }

    @Override
    public String getDescription() {
        return computer.getDescription() + ", Extra 16GB RAM";
    }

    @Override
    public double getCost() {
        return computer.getCost() + 80.00;
    }
}
