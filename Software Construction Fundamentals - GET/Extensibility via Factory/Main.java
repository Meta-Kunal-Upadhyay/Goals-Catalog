import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Screen screen = new Screen(800, 600);

        while (true) {
            System.out.println("\nChoose a shape to add:");
            System.out.println("1. Circle");
            System.out.println("2. Square");
            System.out.println("3. Rectangle");
            System.out.println("4. Triangle");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 5) {
                break;
            }

            System.out.print("Enter origin (x y): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Point origin = new Point(x, y);

            List<Integer> dimension = new ArrayList<>();

            ShapeType type = null;
            switch (choice) {
                case 1:
                    type = ShapeType.CIRCLE;
                    System.out.print("Enter radius: ");
                    dimension.add(scanner.nextInt());
                    break;
                case 2:
                    type = ShapeType.SQUARE;
                    System.out.print("Enter side length: ");
                    dimension.add(scanner.nextInt());
                    break;
                case 3:
                    type = ShapeType.RECTANGLE;
                    System.out.print("Enter length and breadth: ");
                    dimension.add(scanner.nextInt());
                    dimension.add(scanner.nextInt());
                    break;
                case 4:
                    type = ShapeType.TRIANGLE;
                    System.out.print("Enter base and height: ");
                    dimension.add(scanner.nextInt());
                    dimension.add(scanner.nextInt());
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
                    continue;
            }

            Shape shape = ShapeFactory.CreateShape(type, origin, dimension);
            screen.addShape(shape);
            System.out.println(type + " added successfully!");
        }

        System.out.println("\nTotal shapes on screen:");
        screen.printShapesSorted();
        System.out.println();
        List<Shape> sortedShapes;
        System.out.println("Shapes in sorted order on Area basis");
        sortedShapes = screen.sortedShapes("Area");
        for (Shape shape : sortedShapes) {
            System.out.println(shape.getType());
        }
        System.out.println();
        System.out.println("Shapes in sorted order on Perimeter basis");
        sortedShapes = screen.sortedShapes("Perimeter");
        for (Shape shape : sortedShapes) {
            System.out.println(shape.getType());
        }
        System.out.println();
        System.out.println("Shapes in sorted order on Distance basis");
        sortedShapes = screen.sortedShapes("Distance");
        for (Shape shape : sortedShapes) {
            System.out.println(shape.getType());
        }

        System.out.print("\nEnter a point (x y) to check for enclosing shapes: ");
        int px = scanner.nextInt();
        int py = scanner.nextInt();
        Point checkPoint = new Point(px, py);

        List<Shape> enclosingShapes = screen.getShapeEnclosesPoint(checkPoint);
        System.out.println("\nTotal Enclosing shapes on screen for point: (" + px + ", " + py + ")");
        if (enclosingShapes.isEmpty()) {
            System.out.println("No shape is enclosing the point.");
        } else {
            for (Shape shape : enclosingShapes) {
                System.out.println(shape.getType());
            }
        }

        scanner.close();
    }
}
