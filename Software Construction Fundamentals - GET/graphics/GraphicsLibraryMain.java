package graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import graphics.Shape;
import graphics.Shape.ShapeType;
import graphics.Screen;
import graphics.Screen.SortCriteria;
import graphics.Point;
import graphics.ShapeFactory;

public class GraphicsLibraryMain {
    private static Scanner scanner = new Scanner(System.in);
    private static Screen screen;

    public static void main(String[] args) {
        initializeScreen();
        int choice;
        do {
            displayMenu();
            choice = getIntInput("Enter your choice: ");
            processChoice(choice);
        } while (choice != 0);

        System.out.println("Thank you for using the Graphics Library!");
        scanner.close();
    }

    private static void initializeScreen() {
        System.out.println("Welcome to the Graphics Library!");
        double xMax = getDoubleInput("Enter maximum X coordinate for the screen: ");
        double yMax = getDoubleInput("Enter maximum Y coordinate for the screen: ");
        screen = new Screen(xMax, yMax);
        System.out.println("Screen created with dimensions: " + xMax + " x " + yMax);
    }

    private static void displayMenu() {
        System.out.println("\n======= Graphics Library Menu =======");
        System.out.println("1. Add a Shape");
        System.out.println("2. Delete a Shape");
        System.out.println("3. Delete all Shapes of a Type");
        System.out.println("4. Display Shapes sorted by Area");
        System.out.println("5. Display Shapes sorted by Perimeter");
        System.out.println("6. Display Shapes sorted by Timestamp");
        System.out.println("7. Display Shapes sorted by Origin Distance");
        System.out.println("8. Find Shapes Enclosing a Point");
        System.out.println("9. Find Shapes on Top of another Shape");
        System.out.println("10. Display Statistics");
        System.out.println("0. Exit");
        System.out.println("====================================");
    }

    private static void processChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    addShape();
                    break;
                case 2:
                    deleteShape();
                    break;
                case 3:
                    deleteShapesByType();
                    break;
                case 4:
                    displaySortedShapes(Screen.SortCriteria.AREA);
                    break;
                case 5:
                    displaySortedShapes(Screen.SortCriteria.PERIMETER);
                    break;
                case 6:
                    displaySortedShapes(Screen.SortCriteria.TIMESTAMP);
                    break;
                case 7:
                    displaySortedShapes(Screen.SortCriteria.ORIGIN_DISTANCE);
                    break;
                case 8:
                    findShapesEnclosingPoint();
                    break;
                case 9:
                    findShapesOnTop();
                    break;
                case 10:
                    displayStatistics();
                    break;
                case 0:
                    // Exit, handled in the main loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addShape() {
        System.out.println("\n--- Add a Shape ---");
        System.out.println("Available shape types:");
        for (Shape.ShapeType type : Shape.ShapeType.values()) {
            System.out.println("- " + type);
        }

        Shape.ShapeType shapeType = getShapeType();
        
        System.out.println("Enter origin point coordinates:");
        double x = getDoubleInput("X: ");
        double y = getDoubleInput("Y: ");
        Point origin = new Point(x, y);
        
        List<Double> parameters = getShapeParameters(shapeType);
        
        try {
            Shape shape = ShapeFactory.createShape(shapeType, origin, parameters);
            screen.addShape(shape);
            System.out.println("Shape added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating shape: " + e.getMessage());
        }
    }

    private static Shape.ShapeType getShapeType() {
        while (true) {
            System.out.print("Enter shape type (");
            for (Shape.ShapeType type : Shape.ShapeType.values()) {
                System.out.print(type + ", ");
            }
            System.out.print("): ");
            
            String input = scanner.next().toUpperCase();
            try {
                return Shape.ShapeType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid shape type. Please try again.");
            }
        }
    }

    private static List<Double> getShapeParameters(Shape.ShapeType shapeType) {
        List<Double> parameters = new ArrayList<>();
        
        switch (shapeType) {
            case CIRCLE:
                System.out.println("Enter circle parameters:");
                parameters.add(getDoubleInput("Radius: "));
                break;
                
            case SQUARE:
                System.out.println("Enter square parameters:");
                parameters.add(getDoubleInput("Side length: "));
                break;
                
            case RECTANGLE:
                System.out.println("Enter rectangle parameters:");
                parameters.add(getDoubleInput("Length: "));
                parameters.add(getDoubleInput("Width: "));
                break;
                
            case TRIANGLE:
                System.out.println("Enter triangle parameters:");
                System.out.println("First point is the origin you already specified.");
                System.out.println("Enter second point:");
                parameters.add(getDoubleInput("X2: "));
                parameters.add(getDoubleInput("Y2: "));
                System.out.println("Enter third point:");
                parameters.add(getDoubleInput("X3: "));
                parameters.add(getDoubleInput("Y3: "));
                break;
                
            case REGULAR_POLYGON:
                System.out.println("Enter regular polygon parameters:");
                parameters.add((double) getIntInput("Number of sides: "));
                parameters.add(getDoubleInput("Side length: "));
                break;
        }
        
        return parameters;
    }

    private static void deleteShape() {
        System.out.println("\n--- Delete a Shape ---");
        List<Shape> shapes = screen.getSortedShapes(Screen.SortCriteria.TIMESTAMP);
        
        if (shapes.isEmpty()) {
            System.out.println("No shapes to delete.");
            return;
        }
        
        System.out.println("Available shapes:");
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            System.out.println((i + 1) + ". " + shape.getType() + " at " + shape.getOrigin());
        }
        
        int index = getIntInput("Enter the number of the shape to delete (1-" + shapes.size() + "): ");
        if (index < 1 || index > shapes.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        Shape selectedShape = shapes.get(index - 1);
        boolean deleted = screen.deleteShape(selectedShape);
        
        if (deleted) {
            System.out.println("Shape deleted successfully.");
        } else {
            System.out.println("Failed to delete shape.");
        }
    }

    private static void deleteShapesByType() {
        System.out.println("\n--- Delete Shapes by Type ---");
        Shape.ShapeType shapeType = getShapeType();
        
        int count = screen.deleteShapesByType(shapeType);
        System.out.println("Deleted " + count + " shape(s) of type " + shapeType);
    }

    private static void displaySortedShapes(Screen.SortCriteria criteria) {
        System.out.println("\n--- Shapes Sorted by " + criteria + " ---");
        List<Shape> shapes = screen.getSortedShapes(criteria);
        
        if (shapes.isEmpty()) {
            System.out.println("No shapes to display.");
            return;
        }
        
        System.out.printf("%-15s %-20s %-10s %-10s %-10s\n", "Type", "Origin", "Area", "Perimeter", "Timestamp");
        System.out.println("---------------------------------------------------------------------------");
        
        for (Shape shape : shapes) {
            System.out.printf("%-15s %-20s %-10.2f %-10.2f %-10s\n", 
                    shape.getType(), 
                    shape.getOrigin(), 
                    shape.getArea(), 
                    shape.getPerimeter(),
                    shape.getTimestamp().toString().substring(11, 19));
        }
    }

    private static void findShapesEnclosingPoint() {
        System.out.println("\n--- Find Shapes Enclosing a Point ---");
        System.out.println("Enter the point coordinates:");
        double x = getDoubleInput("X: ");
        double y = getDoubleInput("Y: ");
        Point point = new Point(x, y);
        
        List<Shape> shapes = screen.getShapesEnclosingPoint(point);
        
        if (shapes.isEmpty()) {
            System.out.println("No shapes enclosing the point " + point);
            return;
        }
        
        System.out.println("Shapes enclosing the point " + point + ":");
        for (Shape shape : shapes) {
            System.out.println("- " + shape.getType() + " at " + shape.getOrigin());
        }
    }

    private static void findShapesOnTop() {
        System.out.println("\n--- Find Shapes on Top ---");
        List<Shape> shapes = screen.getSortedShapes(Screen.SortCriteria.TIMESTAMP);
        
        if (shapes.isEmpty()) {
            System.out.println("No shapes on the screen.");
            return;
        }
        
        System.out.println("Select a shape to find shapes on top of it:");
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            System.out.println((i + 1) + ". " + shape.getType() + " at " + shape.getOrigin());
        }
        
        int index = getIntInput("Enter the number of the shape (1-" + shapes.size() + "): ");
        if (index < 1 || index > shapes.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        
        Shape selectedShape = shapes.get(index - 1);
        List<Shape> shapesOnTop = screen.getShapesOnTopOf(selectedShape);
        
        if (shapesOnTop.isEmpty()) {
            System.out.println("No shapes on top of the selected shape.");
            return;
        }
        
        System.out.println("Shapes on top of the selected " + selectedShape.getType() + ":");
        for (Shape shape : shapesOnTop) {
            System.out.println("- " + shape.getType() + " at " + shape.getOrigin());
        }
    }

    private static void displayStatistics() {
        System.out.println("\n--- Screen Statistics ---");
        System.out.println("Screen dimensions: " + screen.getXMax() + " x " + screen.getYMax());
        System.out.println("Total number of shapes: " + screen.getShapeCount());
        
        // Count shapes by type
        for (Shape.ShapeType type : Shape.ShapeType.values()) {
            int count = 0;
            for (Shape shape : screen.getSortedShapes(Screen.SortCriteria.TIMESTAMP)) {
                if (shape.getType() == type) {
                    count++;
                }
            }
            System.out.println("Number of " + type + " shapes: " + count);
        }
        
        // Calculate total area and perimeter
        double totalArea = 0;
        double totalPerimeter = 0;
        for (Shape shape : screen.getSortedShapes(Screen.SortCriteria.TIMESTAMP)) {
            totalArea += shape.getArea();
            totalPerimeter += shape.getPerimeter();
        }
        
        System.out.println("Total area of all shapes: " + String.format("%.2f", totalArea));
        System.out.println("Total perimeter of all shapes: " + String.format("%.2f", totalPerimeter));
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Helper method to easily add predefined shapes for testing
    private static void addPredefinedShapes() {
        try {
            // Add a circle
            Shape circle = ShapeFactory.createShape(
                Shape.ShapeType.CIRCLE, 
                new Point(400, 300), 
                List.of(50.0)
            );
            screen.addShape(circle);
            
            // Add a square
            Shape square = ShapeFactory.createShape(
                Shape.ShapeType.SQUARE, 
                new Point(100, 100), 
                List.of(75.0)
            );
            screen.addShape(square);
            
            // Add a rectangle
            Shape rectangle = ShapeFactory.createShape(
                Shape.ShapeType.RECTANGLE, 
                new Point(300, 200), 
                List.of(150.0, 100.0)
            );
            screen.addShape(rectangle);
            
            // Add a triangle
            Shape triangle = ShapeFactory.createShape(
                Shape.ShapeType.TRIANGLE, 
                new Point(500, 400), 
                List.of(550.0, 450.0, 500.0, 500.0)
            );
            screen.addShape(triangle);
            
            // Add a regular polygon (hexagon)
            Shape polygon = ShapeFactory.createShape(
                Shape.ShapeType.REGULAR_POLYGON, 
                new Point(600, 300), 
                List.of(6.0, 50.0)
            );
            screen.addShape(polygon);
            
            System.out.println("Predefined shapes added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding predefined shapes: " + e.getMessage());
        }
    }
}