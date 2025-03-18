import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayrollTest {

    @Test
    void testPayrollProcessing() {
        // Create employees
        Developer dev1 = new Developer("Muskan", "E25/2654", 50000, 4500);
        Developer dev2 = new Developer("Tanmay", "G25/0149", 55000, 4000);
        Manager manager = new Manager("Kunal", "E25/2646", 80000, 10000, 10000);

        // Create departments
        Department devDept = new Department("Development");
        devDept.join(dev1);
        devDept.join(dev2);

        Department manDept = new Department("Management");
        manDept.join(manager);

        // Create organization
        Organization organization = new Organization();
        organization.addDepartment(devDept);
        organization.addDepartment(manDept);

        // Process payroll
        Payroll.processPayroll(organization);
    }
}
