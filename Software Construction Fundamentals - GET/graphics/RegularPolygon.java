
// **Regular Polygon Implementation**
package graphics;

public class RegularPolygon extends AbstractShape {
    private final int sides;
    private final double sideLength;
    private final Point center;
    private final double radius; // Distance from center to any vertex
    
    public RegularPolygon(Point center, int sides, double sideLength) {
        super(calculateOrigin(center, sides, sideLength), ShapeType.REGULAR_POLYGON);
        this.sides = sides;
        this.sideLength = sideLength;
        this.center = center;
        
        // Calculate radius (distance from center to any vertex)
        this.radius = sideLength / (2 * Math.sin(Math.PI / sides));
    }
    
    private static Point calculateOrigin(Point center, int sides, double sideLength) {
        // Calculate the radius (distance from center to any vertex)
        double radius = sideLength / (2 * Math.sin(Math.PI / sides));
        
        // Calculate the coordinates of the leftmost point
        double x = center.getX() - radius;
        double y = center.getY();
        
        return new Point(x, y);
    }
    
    @Override
    public double getArea() {
        return (sides * sideLength * sideLength) / (4 * Math.tan(Math.PI / sides));
    }
    
    @Override
    public double getPerimeter() {
        return sides * sideLength;
    }
    
    @Override
    public boolean isPointEnclosed(Point p) {
        // Check if the point is within the radius of the polygon
        return center.distanceTo(p) <= radius;
    }
    
    @Override
    public boolean overlaps(Shape other) {
        // Simplified approach: check if the other shape's origin is inside this polygon
        return isPointEnclosed(other.getOrigin());
    }
}
