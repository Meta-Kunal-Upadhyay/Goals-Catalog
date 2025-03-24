import java.util.List;

public class ShapeFactory {

    public static Shape CreateShape(ShapeType shapeType, Point origin, List<Integer> dimension) {
        Shape shape = null;
        switch(shapeType){
            case CIRCLE : shape = new Circle(origin, dimension);
            break;
            case RECTANGLE : shape = new Rectangle(origin, dimension);
            break;
            case SQUARE : shape = new Square(origin, dimension);
            break;
            case TRIANGLE : shape = new Triangle(origin, dimension);
            break;
        }
        return shape;
    }
}