
// **Square Implementation**
package graphics;

public class Square extends AbstractShape {
    private final double side;
    
    public Square(Point origin, double side) {
        super(origin, ShapeType.SQUARE);
        this.side = side;
    }
    
    @Override
    public double getArea() {
        return side * side;
    }
    
    @Override
    public double getPerimeter() {
        return 4 * side;
    }
    
    @Override
    public boolean isPointEnclosed(Point p) {
        double x = p.getX();
        double y = p.getY();
        double originX = origin.getX();
        double originY = origin.getY();
        
        return (x >= originX && x <= originX + side) && 
               (y >= originY && y <= originY + side);
    }
    
    @Override
    public boolean overlaps(Shape other) {
        if (other instanceof Square) {
            Square otherSquare = (Square) other;
            double thisLeft = origin.getX();
            double thisRight = origin.getX() + side;
            double thisBottom = origin.getY();
            double thisTop = origin.getY() + side;
            
            double otherLeft = otherSquare.origin.getX();
            double otherRight = otherSquare.origin.getX() + otherSquare.side;
            double otherBottom = otherSquare.origin.getY();
            double otherTop = otherSquare.origin.getY() + otherSquare.side;
            
            return !(thisLeft > otherRight || 
                     thisRight < otherLeft || 
                     thisBottom > otherTop || 
                     thisTop < otherBottom);
        } else {
            // For simplicity, check if the other shape's origin is inside this square
            return isPointEnclosed(other.getOrigin());
        }
    }
}
