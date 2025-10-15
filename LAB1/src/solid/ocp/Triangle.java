package solid.ocp;

/**
 * Triangle class - Implements Shape interface
 * Open/Closed Principle: New shape added without modifying existing code
 */
public class Triangle implements Shape {
    private double base;
    private double height;
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double base, double height, double sideA, double sideB, double sideC) {
        this.base = base;
        this.height = height;
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public String getShapeType() {
        return "Triangle";
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("%s(base=%.2f, height=%.2f)", getShapeType(), base, height);
    }
}