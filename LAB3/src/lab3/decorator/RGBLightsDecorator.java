package lab3.decorator;

public class RGBLightsDecorator extends ComputerDecorator {
    public RGBLightsDecorator(Computer computer) {
        super(computer);
    }

    @Override
    public String getDescription() {
        return computer.getDescription() + ", RGB Lighting Pack";
    }

    @Override
    public double getCost() {
        return computer.getCost() + 45.00;
    }
}
