import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

enum ShapeType{
    CIRCLE, RECTANGLE, SQUARE, TRIANGLE;
}

public interface Shape{
    public double getArea();
    public double getPerimeter();
    public Point getOrigin();
    public boolean isPointEnclosed(Point p);
    public List<Point> getCordinates();
    public ShapeType getType();
    public LocalTime getTimestamp();
}

class Circle implements Shape{
    double radius;
    double PIE = 3.14;
    LocalTime timestamp;
    Point origin;
        Circle(Point origin, List<Integer> dimension){
            this.origin = origin;
            this.radius = dimension.get(0);
            this.timestamp = LocalTime.now();
        } 

    @Override
    public double getArea(){
        return PIE*radius*radius;
    }
    @Override
    public double getPerimeter(){
        return 2*PIE*radius;
    }
    @Override
    public Point getOrigin(){
        return origin;
    }
    @Override
    public boolean isPointEnclosed(Point p){
        Double distance;
        Point midPoints = new Point(origin.getX()+radius, origin.getY()+radius);
        distance = Math.sqrt(Math.pow(midPoints.getX() - p.getX(), 2)+Math.pow(midPoints.getY() - p.getY(), 2));
        if(distance <= radius){
            return true;
        }
        return false;
    }
    @Override
    public List<Point> getCordinates(){
        List<Point> cordinates = new ArrayList<>();
        Point midPoints = new Point(origin.getX()+radius, origin.getY()+radius);
        cordinates.add(new Point(midPoints.getX(), midPoints.getY()+radius));
        cordinates.add(new Point(midPoints.getX()+radius, midPoints.getY()));
        cordinates.add(new Point(midPoints.getX(), midPoints.getY()-radius));
        cordinates.add(new Point(midPoints.getX()-radius, midPoints.getY()));      
        return cordinates;
    }
    @Override
    public ShapeType getType(){
        return ShapeType.CIRCLE;
    }
    @Override
    public LocalTime getTimestamp(){
        return timestamp;
    }
}

class Rectangle implements Shape{
    double length;
    double width;
    LocalTime timestamp;
    Point origin;
        Rectangle(Point origin, List<Integer> dimension){
            this.origin = origin;
            this.length = dimension.get(0);
            this.width = dimension.get(1);
            this.timestamp = LocalTime.now();
        } 

    @Override
    public double getArea(){
        return length*width;
    }
    @Override
    public double getPerimeter(){
        return 2*(length+width);
    }
    @Override
    public Point getOrigin(){
        return origin;
    }
    @Override
    public boolean isPointEnclosed(Point point){
        Point upperRightCoordinates = new Point(origin.getX() + length, origin.getY() + width);
        if((point.getX() >= origin.getX()) && (point.getX() <= upperRightCoordinates.getX()) && (point.getY() >= origin.getY()) && (point.getY() <= upperRightCoordinates.getY())){
            return true;
        }
        return false;
    }
    @Override
    public List<Point> getCordinates(){
        List<Point> cordinates = new ArrayList<>();
        cordinates.add(new Point(origin.getX(), origin.getY()));
        cordinates.add(new Point(origin.getX() + length, origin.getY()));
        cordinates.add(new Point(origin.getX() , origin.getY() + width));
        cordinates.add(new Point(origin.getX() + length, origin.getY() + width));   
        return cordinates;
    }
    @Override
    public ShapeType getType(){
        return ShapeType.RECTANGLE;
    }
    @Override
    public LocalTime getTimestamp(){
        return timestamp;
    }
}

class Square implements Shape{
    double side;
    LocalTime timestamp;
    Point origin;
        Square(Point origin, List<Integer> dimension){
            this.origin = origin;
            this.side = dimension.get(0);
            this.timestamp = LocalTime.now();
        } 

    @Override
    public double getArea(){
        return side*side;
    }
    @Override
    public double getPerimeter(){
        return 4*side;
    }
    @Override
    public Point getOrigin(){
        return origin;
    }
    @Override
    public boolean isPointEnclosed(Point point){
        Point upperRightCoordinates = new Point(origin.getX() + side, origin.getY() + side);
        if((point.getX() >= origin.getX()) && (point.getX() <= upperRightCoordinates.getX()) && (point.getY() >= origin.getY()) && (point.getY() <= upperRightCoordinates.getY())){
            return true;
        }
        return false;
    }
    @Override
    public List<Point> getCordinates(){
        List<Point> cordinates = new ArrayList<>();
        cordinates.add(new Point(origin.getX(), origin.getY()));
        cordinates.add(new Point(origin.getX() + side, origin.getY()));
        cordinates.add(new Point(origin.getX() , origin.getY() + side));
        cordinates.add(new Point(origin.getX() + side, origin.getY() + side));       
        return cordinates;
    }
    @Override
    public ShapeType getType(){
        return ShapeType.SQUARE;
    }
    @Override
    public LocalTime getTimestamp(){
        return timestamp;
    }
}
class Triangle implements Shape {
    double base;
    double height;
    LocalTime timestamp;
    Point origin;
    List<Point> vertices;

    Triangle(Point origin, List<Integer> dimension) {
        this.origin = origin;
        this.base = dimension.get(0);
        this.height = dimension.get(1);
        this.timestamp = LocalTime.now();

        this.vertices = new ArrayList<>();
        vertices.add(new Point(origin.getX(), origin.getY())); 
        vertices.add(new Point(origin.getX() + base, origin.getY())); 
        vertices.add(new Point(origin.getX() + base / 2, origin.getY() + height)); 
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    @Override
    public double getPerimeter() {
        double side1 = Math.sqrt(Math.pow(base / 2, 2) + Math.pow(height, 2));
        double side2 = side1;
        double side3 = base;
        return side1 + side2 + side3;
    }

    @Override
    public Point getOrigin() {
        return origin;
    }

    @Override
    public boolean isPointEnclosed(Point point) {

        Point A = vertices.get(0);
        Point B = vertices.get(1);
        Point C = vertices.get(2);

        double denominator = (B.getY() - C.getY()) * (A.getX() - C.getX()) + (C.getX() - B.getX()) * (A.getY() - C.getY());
        double a = ((B.getY() - C.getY()) * (point.getX() - C.getX()) + (C.getX() - B.getX()) * (point.getY() - C.getY())) / denominator;
        double b = ((C.getY() - A.getY()) * (point.getX() - C.getX()) + (A.getX() - C.getX()) * (point.getY() - C.getY())) / denominator;
        double c = 1 - a - b;

        return (a >= 0 && a <= 1) && (b >= 0 && b <= 1) && (c >= 0 && c <= 1);
    }

    @Override
    public List<Point> getCordinates() {
        return vertices;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.TRIANGLE;
    }

    @Override
    public LocalTime getTimestamp() {
        return timestamp;
    }
}