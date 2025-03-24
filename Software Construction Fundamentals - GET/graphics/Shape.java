// **Shape Interface**
package graphics;

import java.time.Instant;

public interface Shape {
    enum ShapeType {
        CIRCLE,
        SQUARE,
        RECTANGLE,
        TRIANGLE,
        REGULAR_POLYGON
    }
    
    double getArea();
    double getPerimeter();
    Point getOrigin();
    boolean isPointEnclosed(Point p);
    ShapeType getType();
    Instant getTimestamp();
    boolean overlaps(Shape other);
}
