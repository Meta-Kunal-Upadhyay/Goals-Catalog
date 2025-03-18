import java.util.ArrayList;
import java.util.List;

class Organization {
    List<Department> departments;
    
    Organization() {
        departments = new ArrayList<>();
    }

    boolean addDepartment(Department department) {
        return departments.add(department);
    }

    List<Employee> getAllEmployees() {
        List<Employee> allEmployees = new ArrayList<>();
        for (Department department : departments) {
            allEmployees.addAll(department.employees);
        }
        return allEmployees;
    }
}
