import java.util.ArrayList;
import java.util.List;

class Department {
    String departmentName;
    List<Employee> employees;
    
    Department(String departmentName) {
        this.departmentName = departmentName;
        this.employees = new ArrayList<>();
    }

    boolean join(Employee employee) {
        return employees.add(employee);
    }

    boolean relieve(Employee employee) {
        return employees.remove(employee);
    }

    List<Developer> getEmployees() {
        List<Developer> developers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee instanceof Developer) {
                developers.add((Developer) employee);
            }
        }
        return developers;
    }
}
