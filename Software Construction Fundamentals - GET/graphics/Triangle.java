
// **Triangle Implementation**
package graphics;

public class Triangle extends AbstractShape {
    private final Point p1;
    private final Point p2;
    private final Point p3;
    
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, ShapeType.TRIANGLE); // First point is the origin
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    @Override
    public double getArea() {
        return Math.abs((p1.getX() * (p2.getY() - p3.getY()) + 
                         p2.getX() * (p3.getY() - p1.getY()) + 
                         p3.getX() * (p1.getY() - p2.getY())) / 2);
    }
    
    @Override
    public double getPerimeter() {
        return p1.distanceTo(p2) + p2.distanceTo(p3) + p3.distanceTo(p1);
    }
    
    @Override
    public boolean isPointEnclosed(Point p) {
        // Using barycentric coordinates
        double area = getArea();
        double area1 = triangleArea(p, p2, p3);
        double area2 = triangleArea(p1, p, p3);
        double area3 = triangleArea(p1, p2, p);
        
        // Point is inside if sum of sub-triangles equals the total area
        return Math.abs(area - (area1 + area2 + area3)) < 0.00001;
    }
    
    private double triangleArea(Point p1, Point p2, Point p3) {
        return Math.abs((p1.getX() * (p2.getY() - p3.getY()) + 
                         p2.getX() * (p3.getY() - p1.getY()) + 
                         p3.getX() * (p1.getY() - p2.getY())) / 2);
    }
    
    @Override
    public boolean overlaps(Shape other) {
        // For simplicity, we'll check if any point of the other shape is inside this triangle
        // This is a simplified approach and may not be accurate for all cases
        return isPointEnclosed(other.getOrigin());
    }
}
