import java.util.List;

class Payroll {
    private static final double TAX_RATE = 0.10; 
    
    public static double calculateTax(Employee employee) {
        return employee.calculateSalary() * TAX_RATE;
    }

    public static void printSalarySlip(Employee employee) {
        double salary = employee.calculateSalary();
        double tax = calculateTax(employee);
        double netSalary = salary - tax;
        
        System.out.println("Payslip for " + employee.name);
        System.out.println("Basic Salary: " + employee.getBasicSalary());
        System.out.println("Bonus: " + employee.getBonus());
        System.out.println("Compensation: " + employee.getCompensation());
        System.out.println("Total Salary: " + salary);
        System.out.println("Tax: " + tax);
        System.out.println("Net Salary: " + netSalary);
        System.out.println("-----------------------------------");
    }

    public static void processPayroll(Organization organization) {
        List<Employee> employees = organization.getAllEmployees();
        for (Employee employee : employees) {
            printSalarySlip(employee);
        }
    }
}
