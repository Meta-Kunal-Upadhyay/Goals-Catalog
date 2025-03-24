import java.util.*;

/**
 * The Screen class represents a graphical screen where various shapes can be added,
 * deleted, and sorted based on different properties such as area, perimeter, and distance.
 */
public class Screen {
    private Integer XMAX; // Maximum X-coordinate boundary
    private Integer YMAX; // Maximum Y-coordinate boundary
    private List<Shape> shapesList; // List of shapes currently on the screen

    /**
     * Constructs a Screen with specified dimensions.
     *
     * @param XMAX The maximum X-coordinate.
     * @param YMAX The maximum Y-coordinate.
     */
    public Screen(Integer XMAX, Integer YMAX) {
        this.XMAX = XMAX;
        this.YMAX = YMAX;
        this.shapesList = new ArrayList<>();
    }

    /**
     * Checks if the given shape is within the screen boundaries.
     *
     * @param shape The shape to be checked.
     * @return true if the shape is within bounds, false otherwise.
     */
    public boolean inBound(Shape shape) {
        for (Point P : shape.getCordinates()) {
            if (P.getX() < 0 || P.getX() > XMAX || P.getY() < 0 || P.getY() > YMAX) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a shape to the screen if it is within bounds.
     *
     * @param shape The shape to be added.
     * @return true if the shape is added successfully, false otherwise.
     */
    public boolean addShape(Shape shape) {
        if (inBound(shape)) {
            shapesList.add(shape);
            return true;
        }
        return false;
    }

    /**
     * Deletes a shape from the screen.
     *
     * @param shape The shape to be removed.
     * @return true if the shape was present and deleted, false otherwise.
     */
    public boolean deleteShape(Shape shape) {
        return shapesList.remove(shape);
    }

    /**
     * Deletes all shapes of a given type from the screen.
     *
     * @param type The type of shapes to delete.
     */
    public void deleteAllShape(ShapeType type) {
        shapesList.removeIf(shape -> shape.getType() == type);
    }

    /**
     * Prints all shapes on the screen sorted by their timestamp.
     */
    public void printShapesSorted() {
        for (Shape shape : shapesList) {
            System.out.println(shape.getType() + " TimeStamp : " + shape.getTimestamp());
        }
    }

    /**
     * Returns a list of shapes sorted based on the given basis.
     *
     * @param basis The criterion for sorting ("Area", "Perimeter", "Distance").
     * @return A sorted list of shapes.
     */
    public List<Shape> sortedShapes(String basis) {
        List<Shape> sortedShapesList = new ArrayList<>(shapesList);

        switch (basis) {
            case "Area":
                sortedShapesList.sort(Comparator.comparingDouble(Shape::getArea));
                break;
            case "Perimeter":
                sortedShapesList.sort(Comparator.comparingDouble(Shape::getPerimeter));
                break;
            case "Distance":
                sortedShapesList.sort(Comparator.comparingDouble(s -> s.getOrigin().distanceFromOrigin()));
                break;
            default:
                System.out.println("Invalid sorting criteria.");
                return Collections.emptyList();
        }
        return sortedShapesList;
    }

    /**
     * Retrieves a list of shapes that enclose a given point.
     *
     * @param p The point to check.
     * @return A list of shapes enclosing the point.
     */
    public List<Shape> getShapeEnclosesPoint(Point p) {
        List<Shape> enclosingShapes = new ArrayList<>();
        for (Shape shape : shapesList) {
            if (shape.isPointEnclosed(p)) {
                enclosingShapes.add(shape);
            }
        }
        return enclosingShapes;
    }
}