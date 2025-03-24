
// **Rectangle Implementation**
package graphics;

public class Rectangle extends AbstractShape {
    private final double length;
    private final double width;
    
    public Rectangle(Point origin, double length, double width) {
        super(origin, ShapeType.RECTANGLE);
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double getArea() {
        return length * width;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }
    
    @Override
    public boolean isPointEnclosed(Point p) {
        double x = p.getX();
        double y = p.getY();
        double originX = origin.getX();
        double originY = origin.getY();
        
        return (x >= originX && x <= originX + length) && 
               (y >= originY && y <= originY + width);
    }
    
    @Override
    public boolean overlaps(Shape other) {
        if (other instanceof Rectangle) {
            Rectangle otherRect = (Rectangle) other;
            double thisLeft = origin.getX();
            double thisRight = origin.getX() + length;
            double thisBottom = origin.getY();
            double thisTop = origin.getY() + width;
            
            double otherLeft = otherRect.origin.getX();
            double otherRight = otherRect.origin.getX() + otherRect.length;
            double otherBottom = otherRect.origin.getY();
            double otherTop = otherRect.origin.getY() + otherRect.width;
            
            return !(thisLeft > otherRight || 
                     thisRight < otherLeft || 
                     thisBottom > otherTop || 
                     thisTop < otherBottom);
        } else {
            // For simplicity, check if the other shape's origin is inside this rectangle
            return isPointEnclosed(other.getOrigin());
        }
    }
}
