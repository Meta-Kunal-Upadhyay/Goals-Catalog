
import java.util.Scanner;

class HexCalc {

    private static int hexaToDecimal(String hexStr) {
        int decimalValue = 0;
        for (int i = 0; i < hexStr.length(); i++) {
            char hexDigit = Character.toUpperCase(hexStr.charAt(i)); 
            int digitValue;
            
            if (hexDigit >= '0' && hexDigit <= '9') {
                digitValue = hexDigit - '0'; 
            } else {
                digitValue = hexDigit - 'A' + 10; 
            }

            System.out.println("The value " + digitValue);
            decimalValue = decimalValue * 16 + digitValue; 
        }
        return decimalValue;
    }
    
    private static String decimalToHexa(int decimalValue) {
        if (decimalValue == 0) return "0";
    
        StringBuilder hexStr = new StringBuilder();
        while (decimalValue > 0) {
            int remainder = decimalValue % 16;
            hexStr.insert(0, remainder < 10 ? (char) ('0' + remainder) : (char) ('A' + (remainder - 10)));
            decimalValue /= 16;
        }
        return hexStr.toString();
    }
    
    public static String add(String hex1, String hex2) {
        return decimalToHexa(hexaToDecimal(hex1) + hexaToDecimal(hex2));
    }

    public static String subtract(String hex1, String hex2) {
        return decimalToHexa(hexaToDecimal(hex1) - hexaToDecimal(hex2));
    }

    public static String multiply(String hex1, String hex2) {
        return decimalToHexa(hexaToDecimal(hex1) * hexaToDecimal(hex2));
    }

    public static String divide(String hex1, String hex2) {
        int divisor = hexaToDecimal(hex2);
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return decimalToHexa(hexaToDecimal(hex1) / divisor);
    }


    public static boolean isEqual(String hex1, String hex2) {
        return hex1.equalsIgnoreCase(hex2);
    }

    public static boolean isGreater(String hex1, String hex2) {
        if (hex1.length() != hex2.length()) {
            return hex1.length() > hex2.length();
        }
        return hex1.compareToIgnoreCase(hex2) > 0;
    }

    public static boolean isLesser(String hex1, String hex2) {
        return !isGreater(hex1, hex2) && !isEqual(hex1, hex2);
    }


    public static int toDecimal(String hexStr) {
        return hexaToDecimal(hexStr);
    }

    public static String toHex(int decimalValue) {
        return decimalToHexa(decimalValue);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hex1, hex2;
        int decimalValue;

        while (true) {
            System.out.println("\nHexadecimal Calculator:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Compare (==)");
            System.out.println("6. Compare (>)");
            System.out.println("7. Compare (<)");
            System.out.println("8. Convert Hex to Decimal");
            System.out.println("9. Convert Decimal to Hex");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter first hex number: ");
                    hex1 = scanner.next();
                    System.out.print("Enter second hex number: ");
                    hex2 = scanner.next();
                    System.out.println("Result: " + add(hex1, hex2));
                    break;

                case 2:
                    System.out.print("Enter first hex number: ");
                    hex1 = scanner.next();
                    System.out.print("Enter second hex number: ");
                    hex2 = scanner.next();
                    System.out.println("Result: " + subtract(hex1, hex2));
                    break;

                case 3:
                    System.out.print("Enter first hex number: ");
                    hex1 = scanner.next();
                    System.out.print("Enter second hex number: ");
                    hex2 = scanner.next();
                    System.out.println("Result: " + multiply(hex1, hex2));
                    break;

                case 4:
                    System.out.print("Enter first hex number: ");
                    hex1 = scanner.next();
                    System.out.print("Enter second hex number: ");
                    hex2 = scanner.next();
                    try {
                        System.out.println("Result: " + divide(hex1, hex2));
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Enter first hex number: ");
                    hex1 = scanner.next();
                    System.out.print("Enter second hex number: ");
                    hex2 = scanner.next();
                    System.out.println("Equal: " + isEqual(hex1, hex2));
                    break;

                case 6:
                    System.out.print("Enter first hex number: ");
                    hex1 = scanner.next();
                    System.out.print("Enter second hex number: ");
                    hex2 = scanner.next();
                    System.out.println("Greater: " + isGreater(hex1, hex2));
                    break;

                case 7:
                    System.out.print("Enter first hex number: ");
                    hex1 = scanner.next();
                    System.out.print("Enter second hex number: ");
                    hex2 = scanner.next();
                    System.out.println("Lesser: " + isLesser(hex1, hex2));
                    break;

                case 8:
                    System.out.print("Enter a hex number: ");
                    hex1 = scanner.next();
                    System.out.println("Decimal: " + toDecimal(hex1));
                    break;

                case 9:
                    System.out.print("Enter a decimal number: ");
                    decimalValue = scanner.nextInt();
                    System.out.println("Hex: " + toHex(decimalValue));
                    break;

                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}

