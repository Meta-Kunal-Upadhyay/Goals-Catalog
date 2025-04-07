import java.util.*;

public class NoDuplicates {
    public static void main(String[] args) {
        Set<Employee> employeeSet = new HashSet<>();
        
        // Adding employees to the set
        employeeSet.add(new Employee(101, "Kunal Upadhyay", "Sitapura, Jaipur"));
        employeeSet.add(new Employee(102, "Muskan Gupta", "Pratap Nagar, Jaipur"));
        employeeSet.add(new Employee(103, "Prateek Khandelwal", ""));
        employeeSet.add(new Employee(104, "Raj", "Pratap Nagar, Jaipur"));
        employeeSet.add(new Employee(105, "Prakash Sirvi", "Jagatpura, Jaipur"));

        // Trying to add a duplicate employee (same empId)
        employeeSet.add(new Employee(101, "Kunal Upadhyay", "Sitapura, Jaipur")); // Duplicate

        // Printing all employees (no duplicates)
        for (Employee employee : employeeSet) {
            System.out.println(employee);
        }
    }
}


// javac Employee.java SortByEmpId.java SortByName.java NoDuplicates.java

// java SortByEmpId java SortByName java NoDuplicates