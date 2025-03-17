class Area {

    public double getTriangleArea(double width, double height) throws ArithmeticException {
        if (width <= 0 || height <= 0) {
            throw new ArithmeticException("Width and height must be greater than zero.");
        }
        return 0.5 * width * height;
    }

    
    public double getRectangleArea(double width, double height) throws ArithmeticException {
        if (width <= 0 || height <= 0) {
            throw new ArithmeticException("Width and height must be greater than zero.");
        }
        return width * height;
    }

    
    public double getSquareArea(double width) throws ArithmeticException {
        if (width <= 0) {
            throw new ArithmeticException("Side length must be greater than zero.");
        }
        return width * width;
    }

    
    public double getCircleArea(double radius) throws ArithmeticException {
        if (radius <= 0) {
            throw new ArithmeticException("Radius must be greater than zero.");
        }
        return 3.14 * radius * radius;
    }

    
    public static void main(String[] args) {
        Area areaCalculator = new Area();

        try {
            System.out.println("Triangle Area: " + areaCalculator.getTriangleArea(5.0, 10.0));
            System.out.println("Rectangle Area: " + areaCalculator.getRectangleArea(4.0, 7.0));
            System.out.println("Square Area: " + areaCalculator.getSquareArea(6.0));
            System.out.println("Circle Area: " + areaCalculator.getCircleArea(3.5));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}