package solid.ocp;

import java.util.List;

/**
 * AreaCalculator class - Calculates total area for any collection of shapes
 * Open/Closed Principle: Can work with new shapes without modification
 */
public class AreaCalculator {

    public double calculateTotalArea(List<Shape> shapes) {
        return shapes.stream()
                    .mapToDouble(Shape::calculateArea)
                    .sum();
    }

    public double calculateTotalPerimeter(List<Shape> shapes) {
        return shapes.stream()
                    .mapToDouble(Shape::calculatePerimeter)
                    .sum();
    }

    public void printShapeDetails(List<Shape> shapes) {
        System.out.println("Shape Details:");
        for (Shape shape : shapes) {
            System.out.printf("%s - Area: %.2f, Perimeter: %.2f%n",
                shape.toString(),
                shape.calculateArea(),
                shape.calculatePerimeter());
        }
        System.out.printf("Total Area: %.2f%n", calculateTotalArea(shapes));
        System.out.printf("Total Perimeter: %.2f%n", calculateTotalPerimeter(shapes));
    }
}