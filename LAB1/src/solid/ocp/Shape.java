package solid.ocp;

/**
 * Shape interface - Defines the contract for all shapes
 * Open/Closed Principle: Open for extension (new shapes), closed for modification
 */
public interface Shape {
    double calculateArea();
    double calculatePerimeter();
    String getShapeType();
}