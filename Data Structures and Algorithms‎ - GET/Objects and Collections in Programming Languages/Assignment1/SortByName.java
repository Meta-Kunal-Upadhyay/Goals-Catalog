import java.util.*;

public class SortByName {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        
        // Adding employees to the list
        employees.add(new Employee(101, "Kunal Upadhyay", "Sitapura, Jaipur"));
        employees.add(new Employee(102, "Muskan Gupta", "Pratap Nagar, Jaipur"));
        employees.add(new Employee(103, "Prateek Khandelwal", ""));
        employees.add(new Employee(104, "Raj", "Pratap Nagar, Jaipur"));
        employees.add(new Employee(105, "Prakash Sirvi", "Jagatpura, Jaipur"));

        // Sorting employees by name
        employees.sort(Comparator.comparing(Employee::getName));

        // Printing sorted employees by name
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
