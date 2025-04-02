import java.util.*;

public class Operator {
    // Define the precedence and associativity
    public static final Map<String, Integer> precedence = new HashMap<>();
    public static final Map<String, String> associativity = new HashMap<>();

    static {
        precedence.put("(", 0);
        precedence.put(")", 0);
        precedence.put("!", 4); // unary
        precedence.put("*", 3);
        precedence.put("/", 3);
        precedence.put("+", 2);
        precedence.put("-", 2);
        precedence.put("==", 1);
        precedence.put("!=", 1);
        precedence.put("<", 1);
        precedence.put(">", 1);
        precedence.put("<=", 1);
        precedence.put(">=", 1);
        precedence.put("&&", 0);
        precedence.put("||", 0);

        // Associativity of operators: Left or Right
        associativity.put("!", "RIGHT");
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

    public static int getPrecedence(String operator) {
        return precedence.getOrDefault(operator, -1);
    }

    public static String getAssociativity(String operator) {
        return associativity.getOrDefault(operator, "LEFT");
    }
}
