
// **Shape Factory**
package graphics;

import java.util.List;

public class ShapeFactory {
    public static Shape createShape(Shape.ShapeType type, Point point, List<Double> parameters) {
        switch (type) {
            case CIRCLE:
                if (parameters.size() != 1) {
                    throw new IllegalArgumentException("Circle requires exactly one parameter: radius");
                }
                return new Circle(point, parameters.get(0));
                
            case SQUARE:
                if (parameters.size() != 1) {
                    throw new IllegalArgumentException("Square requires exactly one parameter: side length");
                }
                return new Square(point, parameters.get(0));
                
            case RECTANGLE:
                if (parameters.size() != 2) {
                    throw new IllegalArgumentException("Rectangle requires exactly two parameters: length and width");
                }
                return new Rectangle(point, parameters.get(0), parameters.get(1));
                
            case TRIANGLE:
                if (parameters.size() != 4) {
                    throw new IllegalArgumentException("Triangle requires exactly four parameters: x2, y2, x3, y3");
                }
                Point p2 = new Point(parameters.get(0), parameters.get(1));
                Point p3 = new Point(parameters.get(2), parameters.get(3));
                return new Triangle(point, p2, p3);
                
            case REGULAR_POLYGON:
                if (parameters.size() != 2) {
                    throw new IllegalArgumentException("Regular polygon requires exactly two parameters: sides and side length");
                }
                return new RegularPolygon(point, parameters.get(0).intValue(), parameters.get(1));
                
            default:
                throw new IllegalArgumentException("Unsupported shape type: " + type);
        }
    }
}
