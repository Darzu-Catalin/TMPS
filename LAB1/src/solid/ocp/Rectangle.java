package solid.ocp;

/**
 * Rectangle class - Implements Shape interface
 * Open/Closed Principle: Extends functionality without modifying existing code
 */
public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String getShapeType() {
        return "Rectangle";
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return String.format("%s(width=%.2f, height=%.2f)", getShapeType(), width, height);
    }
}