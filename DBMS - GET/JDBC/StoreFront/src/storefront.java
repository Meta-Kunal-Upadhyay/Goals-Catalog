// This code is for establishing connection with MySQL
// database and retrieving data
// from db Java Database connectivity

/*
 *1. import --->java.sql
 *2. load and register the driver ---> com.jdbc.
 *3. create connection
 *4. create a statement
 *5. execute the query
 *6. process the results
 *7. close
 */

 import java.sql.*;

 class storefront {
     public static void main(String[] args) throws Exception {
         String url = "jdbc:mysql://localhost:3306/storefront"; // Database details
         String username = "root"; // MySQL credentials
         String password = "root";
         String query = "select * from users"; // Query to be run
 
         // Load and register the driver
         Class.forName("com.mysql.cj.jdbc.Driver");
 
         // Establish connection
         Connection con = DriverManager.getConnection(url, username, password);
         System.out.println("Connection Established successfully");
 
         // Create a statement
         Statement st = con.createStatement();
 
         // Execute the query
         ResultSet rs = st.executeQuery(query);
 
         // Process the results
         while (rs.next()) {
             String name = rs.getString("username"); // Retrieve name from db
             System.out.println(name); // Print result on console
         }
 
         // Close the statement and connection
         st.close();
         con.close();
         System.out.println("Connection Closed....");
     }
 }
 

import java.sql.*;
import java.util.*;

// POJO Classes
class Order {
    int orderId;
    Date orderDate;
    double orderTotal;

    public Order(int orderId, Date orderDate, double orderTotal) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }
}

class DatabaseHelper {
    String url = "jdbc:mysql://localhost:3306/storefront"; // Database details
    String username = "root"; // MySQL credentials
    String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<Order> getShippedOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT order_id, order_date, total_amount FROM Orders " +
                       "WHERE user_id = ? AND order_status = 'Shipped' " +
                       "ORDER BY order_date ASC";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("order_id"), rs.getDate("order_date"), rs.getDouble("total_amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static void insertProductImages(int productId, List<String> imageUrls) {
        String query = "INSERT INTO Product_Images (product_id, image_url) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            for (String url : imageUrls) {
                stmt.setInt(1, productId);
                stmt.setString(2, url);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int deleteUnorderedProductsLastYear() {
        String query = "DELETE FROM Products WHERE product_id NOT IN " +
                       "(SELECT DISTINCT product_id FROM Order_Items WHERE order_id IN " +
                       "(SELECT order_id FROM Orders WHERE order_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)))";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Map<String, Integer> getTopParentCategories() {
        Map<String, Integer> result = new TreeMap<>();
        String query = "SELECT c1.category_name, COUNT(c2.category_id) AS child_count " +
                       "FROM Categories c1 LEFT JOIN Categories c2 ON c1.category_id = c2.parent_category_id " +
                       "WHERE c1.parent_category_id IS NULL " +
                       "GROUP BY c1.category_name ORDER BY c1.category_name ASC";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                result.put(rs.getString("category_name"), rs.getInt("child_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}