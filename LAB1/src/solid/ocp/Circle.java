package solid.ocp;

/**
 * Circle class - Implements Shape interface
 * Open/Closed Principle: Extends functionality without modifying existing code
 */
public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getShapeType() {
        return "Circle";
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return String.format("%s(radius=%.2f)", getShapeType(), radius);
    }
}