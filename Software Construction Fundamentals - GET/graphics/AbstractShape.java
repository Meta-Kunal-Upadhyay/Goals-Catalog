// **Abstract Shape Class**
package graphics;

import java.time.Instant;

public abstract class AbstractShape implements Shape {
    protected Point origin;
    protected Instant timestamp;
    protected ShapeType type;
    
    public AbstractShape(Point origin, ShapeType type) {
        this.origin = origin;
        this.timestamp = Instant.now();
        this.type = type;
    }
    
    @Override
    public Point getOrigin() {
        return origin;
    }
    
    @Override
    public Instant getTimestamp() {
        return timestamp;
    }
    
    @Override
    public ShapeType getType() {
        return type;
    }
}
