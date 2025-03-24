
// **Screen Class**
package graphics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Screen {
    private final double xMax;
    private final double yMax;
    private final List<Shape> shapes;
    
    public enum SortCriteria {
        AREA,
        PERIMETER,
        TIMESTAMP,
        ORIGIN_DISTANCE
    }
    
    public Screen(double xMax, double yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.shapes = new ArrayList<>();
    }
    
    public void addShape(Shape shape) {
        // Validate that the shape is within screen bounds
        Point origin = shape.getOrigin();
        if (origin.getX() < 0 || origin.getX() > xMax || origin.getY() < 0 || origin.getY() > yMax) {
            throw new IllegalArgumentException("Shape origin is outside screen bounds");
        }
        shapes.add(shape);
    }
    
    public boolean deleteShape(Shape shape) {
        return shapes.remove(shape);
    }
    
    public int deleteShapesByType(Shape.ShapeType type) {
        int count = 0;
        Iterator<Shape> iterator = shapes.iterator();
        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            if (shape.getType() == type) {
                iterator.remove();
                count++;
            }
        }
        return count;
    }
    
    public List<Shape> getSortedShapes(SortCriteria criteria) {
        Comparator<Shape> comparator;
        
        switch (criteria) {
            case AREA:
                comparator = Comparator.comparingDouble(Shape::getArea);
                break;
            case PERIMETER:
                comparator = Comparator.comparingDouble(Shape::getPerimeter);
                break;
            case TIMESTAMP:
                comparator = Comparator.comparing(Shape::getTimestamp);
                break;
            case ORIGIN_DISTANCE:
                comparator = Comparator.comparingDouble(s -> s.getOrigin().distanceToOrigin());
                break;
            default:
                throw new IllegalArgumentException("Unsupported sort criteria: " + criteria);
        }
        
        return shapes.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    
    public List<Shape> getShapesEnclosingPoint(Point point) {
        return shapes.stream()
                .filter(shape -> shape.isPointEnclosed(point))
                .collect(Collectors.toList());
    }
    
    public List<Shape> getShapesOnTopOf(Shape targetShape) {
        return shapes.stream()
                .filter(shape -> shape.getTimestamp().isAfter(targetShape.getTimestamp()))
                .filter(shape -> shape.overlaps(targetShape))
                .collect(Collectors.toList());
    }
    
    public double getXMax() {
        return xMax;
    }
    
    public double getYMax() {
        return yMax;
    }
    
    public int getShapeCount() {
        return shapes.size();
    }
}