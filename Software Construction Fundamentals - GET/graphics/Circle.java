
// **Circle Implementation**
package graphics;

public class Circle extends AbstractShape {
    private final double radius;
    private final Point center;
    
    public Circle(Point center, double radius) {
        super(new Point(center.getX() - radius, center.getY()), ShapeType.CIRCLE);
        this.radius = radius;
        this.center = center;
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    
    @Override
    public boolean isPointEnclosed(Point p) {
        return center.distanceTo(p) <= radius;
    }
    
    @Override
    public boolean overlaps(Shape other) {
        if (other instanceof Circle) {
            Circle otherCircle = (Circle) other;
            return center.distanceTo(otherCircle.center) < (radius + otherCircle.radius);
        } else {
            // For simplicity, check if the other shape's origin is inside this circle
            // A more accurate implementation would check specific shape overlaps
            return isPointEnclosed(other.getOrigin());
        }
    }
}
