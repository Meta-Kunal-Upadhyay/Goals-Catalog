import java.util.*;

public class InfixEvaluator {

    public static int poww(int a, int b){
        double c = (double) a;
        double d = (double) b;
        return (int) (Math.pow(c,d));
    }

    // Define operator precedence and associativity
    public static final Map<String, Integer> precedence = new HashMap<>();
    public static final Map<String, String> associativity = new HashMap<>();

    static {
        precedence.put("!", 5);
        precedence.put("^", 4); // unary NOT operator
        precedence.put("*", 3);
        precedence.put("/", 3);
        precedence.put("+", 2);
        precedence.put("-", 2);
        precedence.put("<", 1);
        precedence.put(">", 1);
        precedence.put("<=", 1);
        precedence.put(">=", 1);
        precedence.put("==", 1);
        precedence.put("!=", 1);
        precedence.put("&&", 0);
        precedence.put("||", 0);
        precedence.put("(", 6);
        precedence.put(")", 6);

        // Associativity: LEFT or RIGHT
        associativity.put("!", "RIGHT");
        associativity.put("^", "LEFT");
        associativity.put("*", "LEFT");
        associativity.put("/", "LEFT");
        associativity.put("+", "LEFT");
        associativity.put("-", "LEFT");
        associativity.put("==", "LEFT");
        associativity.put("!=", "LEFT");
        associativity.put("<", "LEFT");
        associativity.put(">", "LEFT");
        associativity.put("<=", "LEFT");
        associativity.put(">=", "LEFT");
        associativity.put("&&", "LEFT");
        associativity.put("||", "LEFT");
    }

    // Tokenize the expression into meaningful parts (numbers, operators, parentheses)
    // public static List<String> tokenize(String expression) {
    //     List<String> tokens = new ArrayList<>();
    //     // String regex = "(\\d+|==|!=|<=|>=|&&|\\|\\||[()\\+\\-\\*/<>!])";

    //     // Using regex to match numbers and operators
    //     Scanner sc = new Scanner(expression);
    //     sc.useDelimiter("\\s*");
        
    //     while (sc.hasNext()) {
    //         String part = sc.next().trim();
    //         if (part.matches("\\d+")) { // Numbers
    //             System.out.println(part);
    //             tokens.add(part);
    //         }
    //         else if (part.matches("[()\\+\\-\\*/<>!]=?|&&|\\|\\|")) { // Operators and parentheses
    //             System.out.println(part);
    //             tokens.add(part);
    //         }
    //     }

    //     return tokens;
    // }

    public static List<String> tokenize(String expression){
        List<String> tokens = new ArrayList<>();
        String[] toke = expression.split(" ");
        for(String tok : toke){
            if (tok.matches("[()\\+\\-\\^\\*/<>!]=?|&&|\\|\\|")) { // Operators and parentheses
                // System.out.println(tok);
                tokens.add(tok);
            }
            else if(Integer.parseInt(tok) >= 0 || Integer.parseInt(tok) < 0){
                // System.out.println(tok);
                tokens.add(tok);
            }
        }
        return tokens;
    }

    // Check if the token is a number
    private static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Check if the token is an operator
    private static boolean isOperator(String token) {
        return precedence.containsKey(token);
    }

    // Check operator precedence and associativity
    private static boolean shouldPopOperator(String topOperator, String currentOperator) {
        // Handle precedence and associativity
        if (associativity.get(currentOperator).equals("LEFT")) {
            return precedence.get(topOperator) >= precedence.get(currentOperator);
        } else {
            return precedence.get(topOperator) > precedence.get(currentOperator);
        }
    }

    // Perform the operation based on the operator
    private static int performOperation(int leftOperand, int rightOperand, String operator) {
        switch (operator) {
            case "+": return leftOperand + rightOperand;
            case "-": return leftOperand - rightOperand;
            case "*": return leftOperand * rightOperand;
            case "/": return leftOperand / rightOperand;
            case "^": return (poww(leftOperand,  rightOperand));
            case "==": return leftOperand == rightOperand ? 1 : 0;
            case "!=": return leftOperand != rightOperand ? 1 : 0;
            case "<": return leftOperand < rightOperand ? 1 : 0;
            case ">": return leftOperand > rightOperand ? 1 : 0;
            case "<=": return leftOperand <= rightOperand ? 1 : 0;
            case ">=": return leftOperand >= rightOperand ? 1 : 0;
            case "&&": return (leftOperand != 0 && rightOperand != 0) ? 1 : 0;
            case "||": return (leftOperand != 0 || rightOperand != 0) ? 1 : 0;
            default: throw new UnsupportedOperationException("Unknown operator " + operator);
        }
    }

    // Evaluate the infix expression
    public static int evaluateInfixExpression(String expression) {
        List<String> tokens = tokenize(expression);
        // for(String token : tokens){
        //     System.out.println(token);
        // }
        Stack<Integer> operandStack = new StackImpl<>();
        Stack<String> operatorStack = new StackImpl<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                operandStack.push(Integer.parseInt(token)); // If token is a number, push it to operand stack
            } else if (token.equals("(")) {
                operatorStack.push(token); // If token is "(", push it to operator stack
            } else if (token.equals(")")) {
                // Process operators until "(" is encountered
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    String operator = operatorStack.pop();
                    int rightOperand = operandStack.pop();
                    int leftOperand = operandStack.pop();
                    operandStack.push(performOperation(leftOperand, rightOperand, operator));
                }
                operatorStack.pop(); // Pop the "(" from the stack
            } else if (isOperator(token)) {
                // Process operators based on precedence and associativity
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")
                        && shouldPopOperator(operatorStack.peek(), token)) {
                    String operator = operatorStack.pop();
                    int rightOperand = operandStack.pop();
                    int leftOperand = operandStack.pop();
                    operandStack.push(performOperation(leftOperand, rightOperand, operator));
                }
                operatorStack.push(token); // Push the current operator to the operator stack
            }
        }

        // After processing all tokens, perform any remaining operations
        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            int rightOperand = operandStack.pop();
            int leftOperand = operandStack.pop();
            operandStack.push(performOperation(leftOperand, rightOperand, operator));
        }

        return operandStack.pop(); // The final result will be on top of the operand stack
    }

    public static void main(String[] args) {
        String expression = "2 + 3 * 5 + 50 / 5 ^ 2";
        System.out.println("Result: " + evaluateInfixExpression(expression)); // Test evaluation
    }
}


