 import java.sql.*;

 class checking {
     public static void main(String[] args) throws Exception {
         String url = "jdbc:mysql://localhost:3306/storefront"; // Database details
         String username = "root"; // MySQL credentials
         String password = "root";
         String query = "select * from orders"; // Query to be run
 
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
             int userId = rs.getInt("user_id"); // Retrieve name from db
             Date timee = rs.getDate("order_date");
             String orderStatus = rs.getString("order_status");
             System.out.println("OrderID: " + userId + ", OrderDate: " + timee + ", OrderStatus: " + orderStatus); // Print result on console
         }
 
         // Close the statement and connection
         st.close();
         con.close();
         System.out.println("Connection Closed....");
     }
 }